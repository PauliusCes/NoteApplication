package lt.paulius.noteapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import lt.paulius.noteapplication.databinding.ActivityNoteDetailsBinding
import java.time.LocalDateTime

class NoteDetails : AppCompatActivity() {

    private lateinit var binding: ActivityNoteDetailsBinding

    private var finishIntentStatus = SECOND_ACTIVITY_NOTE_INTENT_RETURN_UPDATE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_note_details)
        binding.noteDetails = this

        getIntentExtra()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(SECOND_ACTIVITY_NOTE_ID, binding.etEditID.text.toString())
            putString(SECOND_ACTIVITY_NOTE_NAME, binding.etEditNoteName.text.toString())
            putString(SECOND_ACTIVITY_NOTE_DETAILS, binding.etEditNote.text.toString())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        with(savedInstanceState) {
            binding.etEditID.setText(this.getString(SECOND_ACTIVITY_NOTE_ID))
            binding.etEditNoteName.setText(this.getString(SECOND_ACTIVITY_NOTE_NAME))
            binding.etEditNote.setText(this.getString(SECOND_ACTIVITY_NOTE_DETAILS))
        }
    }


    private fun getIntentExtra() {
        val noteID: Int = intent.getIntExtra(Notes.MAIN_ACTIVITY_NOTE_ID, -1)
        val name = intent.getStringExtra(Notes.MAIN_ACTIVITY_NOTE_NAME) ?: ""
        val details = intent.getStringExtra(Notes.MAIN_ACTIVITY_NOTE_DETAILS) ?: ""
//        val updateDate = intent.getSerializableExtra(Notes.MAIN_ACTIVITY_NOTE_UPDATE_DATE) as LocalDateTime

        if (noteID >= 0) {
            binding.etEditID.setText(noteID.toString())
            binding.etEditNoteName.setText(name)
            binding.etEditNote.setText(details)
//            updatedDate.setText(updateDate)

        } else {
            finishIntentStatus = SECOND_ACTIVITY_NOTE_INTENT_RETURN_NEW
        }
    }

    fun onClickBackButton(view: View) {
        finish()
    }


    fun onClickSaveButton(view: View) {
        val finishIntent = Intent()

        finishIntent.putExtra(
            SECOND_ACTIVITY_NOTE_ID,
            (binding.etEditID.text.toString().toInt())
        )
        finishIntent.putExtra(
            SECOND_ACTIVITY_NOTE_NAME,
            (binding.etEditNoteName.text.toString())
        )
        finishIntent.putExtra(
            SECOND_ACTIVITY_NOTE_DETAILS,
            (binding.etEditNote.text.toString())
        )
        finishIntent.putExtra(SECOND_ACTIVITY_NOTE_UPDATE_DATE, LocalDateTime.now())

        setResult(finishIntentStatus, finishIntent)
        finish()
    }


    companion object {
        const val SECOND_ACTIVITY_NOTE_ID = "lt.paulius.noteapplication.secondactivity_note_id"
        const val SECOND_ACTIVITY_NOTE_NAME = "lt.paulius.noteapplication.secondactivity_note_name"
        const val SECOND_ACTIVITY_NOTE_DETAILS =
            "lt.paulius.noteapplication.secondactivity_note_details"
        const val SECOND_ACTIVITY_NOTE_UPDATE_DATE =
            "lt.paulius.noteapplication.secondactivity_note_update_date"

        const val SECOND_ACTIVITY_NOTE_INTENT_RETURN_NEW = 101
        const val SECOND_ACTIVITY_NOTE_INTENT_RETURN_UPDATE = 102
    }
}