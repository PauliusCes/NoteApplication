package lt.paulius.noteapplication.notesactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import lt.paulius.noteapplication.CustomAdapter
import lt.paulius.noteapplication.repository.Note
import lt.paulius.noteapplication.notedetailsactivity.NoteDetails
import lt.paulius.noteapplication.R
import lt.paulius.noteapplication.databinding.ActivityMainBinding

class Notes : AppCompatActivity() {

    lateinit var adapter: CustomAdapter
    private lateinit var binding: ActivityMainBinding
    private val activityViewModel: NotesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.notes = this

        setUpListView()

        activityViewModel.notesLiveData.observe(
            this,
            Observer { listOfNotes ->
                adapter.add(listOfNotes)
            }
        )
        setClickNoteDetails()
    }

    override fun onResume() {
        super.onResume()
        activityViewModel.fetchNotes()
    }

    private fun setUpListView() {
        adapter = CustomAdapter(this)
        binding.lvNoteListView.adapter = adapter
    }

    fun setUpOnClickListener(view: View) {
        startActivity(Intent(this, NoteDetails::class.java))
    }

    private fun setClickNoteDetails() {
        binding.lvNoteListView.setOnItemClickListener { adapterView, view, position, l ->
            val note: Note = adapterView.getItemAtPosition(position) as Note

            val noteIntent = Intent(this, NoteDetails::class.java)
            noteIntent.putExtra(NOTE_INTENT_ID, note.id)

            startActivity(noteIntent)
        }
    }

    companion object {
        const val NOTE_INTENT_ID = "lt.paulius.noteapplication.note_intent_object"
    }
}
