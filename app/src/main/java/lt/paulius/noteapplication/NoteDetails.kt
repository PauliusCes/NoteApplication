package lt.paulius.noteapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.time.LocalDateTime

class NoteDetails : AppCompatActivity() {

    lateinit var editID: EditText
    lateinit var noteName: EditText
    lateinit var noteDetails: EditText
    lateinit var saveButton: Button
    lateinit var backButton: Button
//    lateinit var updatedDate: TextView
    private var finishIntentStatus = SECOND_ACTIVITY_NOTE_INTENT_RETURN_UPDATE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_details)

        editID = findViewById(R.id.etEditID)
        noteName = findViewById(R.id.etEditNoteName)
        noteDetails = findViewById(R.id.etEditNote)
        saveButton = findViewById(R.id.btnSaveButton)
        backButton = findViewById(R.id.btnBackButton)
//        updatedDate = findViewById(R.id.tvUpdateDate)

        getIntentExtra()
        setBackButtonOnClickListener()
        setSaveButtonOnClickListener()

    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(SECOND_ACTIVITY_NOTE_ID, editID.text.toString())
            putString(SECOND_ACTIVITY_NOTE_NAME, noteName.text.toString())
            putString(SECOND_ACTIVITY_NOTE_DETAILS, noteDetails.text.toString())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        with(savedInstanceState) {
            editID.setText(this.getString(SECOND_ACTIVITY_NOTE_ID))
            noteName.setText(this.getString(SECOND_ACTIVITY_NOTE_NAME))
            noteDetails.setText(this.getString(SECOND_ACTIVITY_NOTE_DETAILS))
        }
    }


    private fun getIntentExtra() {
        val noteID: Int = intent.getIntExtra(Notes.MAIN_ACTIVITY_NOTE_ID, -1)
        val name = intent.getStringExtra(Notes.MAIN_ACTIVITY_NOTE_NAME) ?: ""
        val details = intent.getStringExtra(Notes.MAIN_ACTIVITY_NOTE_DETAILS) ?: ""
//        val updateDate = intent.getSerializableExtra(Notes.MAIN_ACTIVITY_NOTE_UPDATE_DATE) as LocalDateTime

        if (noteID >= 0) {
            editID.setText(noteID.toString())
            noteName.setText(name)
            noteDetails.setText(details)
//            updatedDate.setText(updateDate)

        } else{
            finishIntentStatus = SECOND_ACTIVITY_NOTE_INTENT_RETURN_NEW
        }
    }

    private fun setBackButtonOnClickListener() {
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun setSaveButtonOnClickListener() {
        saveButton.setOnClickListener {
            val finishIntent = Intent()

            finishIntent.putExtra(SECOND_ACTIVITY_NOTE_ID, (editID.text.toString().toInt()))
            finishIntent.putExtra(SECOND_ACTIVITY_NOTE_NAME, (noteName.text.toString()))
            finishIntent.putExtra(SECOND_ACTIVITY_NOTE_DETAILS, (noteDetails.text.toString()))

            setResult(finishIntentStatus, finishIntent)
            finish()
        }
    }


    companion object {
        const val SECOND_ACTIVITY_NOTE_ID = "lt.paulius.noteapplication.secondactivity_note_id"
        const val SECOND_ACTIVITY_NOTE_NAME = "lt.paulius.noteapplication.secondactivity_note_name"
        const val SECOND_ACTIVITY_NOTE_DETAILS = "lt.paulius.noteapplication.secondactivity_note_details"

        const val SECOND_ACTIVITY_NOTE_INTENT_RETURN_NEW = 101
        const val SECOND_ACTIVITY_NOTE_INTENT_RETURN_UPDATE = 102
    }
}