package lt.paulius.noteapplication

import android.content.ClipData.Item
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Notes : AppCompatActivity() {

    lateinit var addButton: View
    lateinit var noteListView: ListView
    lateinit var adapter: CustomAdaper
    private var noteIndex = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton = findViewById(R.id.fabAddNote)
        noteListView = findViewById(R.id.lvNoteListView)

        val notes = mutableListOf<Note>()

        setUpListView()
        updateAdapter(notes)

        setClickNoteDetails()
        setUpOnClickListener()

    }

    private fun setUpListView() {
        adapter = CustomAdaper(this)
        noteListView.adapter = adapter
    }

    private fun updateAdapter(notes: MutableList<Note>) {
        adapter.add(notes)
        adapter.add(
            Note(1, "Buy stuff", "Have to buy milk", _creationDate = LocalDateTime.now()),
            Note(2, "Diary", "First entry", _creationDate = LocalDateTime.now())
        )
    }

    private fun setUpOnClickListener() {
        addButton.setOnClickListener {
            startActivityForResult.launch(Intent(this, NoteDetails::class.java))
        }
    }

    private fun setClickNoteDetails() {
        noteListView.setOnItemClickListener { adapterView, view, position, l ->
            val note: Note = adapterView.getItemAtPosition(position) as Note

            noteIndex = position

            val noteIntent = Intent(this, NoteDetails::class.java)
            noteIntent.putExtra(MAIN_ACTIVITY_NOTE_ID, note.id)
            noteIntent.putExtra(MAIN_ACTIVITY_NOTE_NAME, note.name)
            noteIntent.putExtra(MAIN_ACTIVITY_NOTE_DETAILS, note.details)

            startActivityForResult.launch(noteIntent)
        }
    }

    private val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                NoteDetails.SECOND_ACTIVITY_NOTE_INTENT_RETURN_NEW -> {
                    val note = Note(
                        _id = result.data
                            ?.getIntExtra(NoteDetails.SECOND_ACTIVITY_NOTE_ID, 0) ?: 0,
                        _name = result.data
                            ?.getStringExtra(NoteDetails.SECOND_ACTIVITY_NOTE_NAME) ?: "",
                        _details = result.data
                            ?.getStringExtra(NoteDetails.SECOND_ACTIVITY_NOTE_DETAILS) ?: "",
                    )
                    adapter.add(note)
                }

                NoteDetails.SECOND_ACTIVITY_NOTE_INTENT_RETURN_UPDATE -> {
                    val note = Note(
                        _id = result.data
                            ?.getIntExtra(NoteDetails.SECOND_ACTIVITY_NOTE_ID, 0) ?: 0,
                        _name = result.data
                            ?.getStringExtra(NoteDetails.SECOND_ACTIVITY_NOTE_NAME) ?: "",
                        _details = result.data
                            ?.getStringExtra(NoteDetails.SECOND_ACTIVITY_NOTE_DETAILS) ?: ""
                    )
                    if (noteIndex >= 0) {
                        adapter.update(noteIndex, note)
                    }
                }

            }
        }

    companion object {
        const val MAIN_ACTIVITY_NOTE_ID = "lt.paulius.noteapplication_nore_id"
        const val MAIN_ACTIVITY_NOTE_NAME = "lt.paulius.noteapplication_nore_name"
        const val MAIN_ACTIVITY_NOTE_DETAILS = "lt.paulius.noteapplication_note_details"
//        const val MAIN_ACTIVITY_NOTE_UPDATE_DATE = "lt.paulius.noteapplication.note_update_date"

    }

}
