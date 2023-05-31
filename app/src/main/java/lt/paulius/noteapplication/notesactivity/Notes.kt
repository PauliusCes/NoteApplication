package lt.paulius.noteapplication.notesactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import lt.paulius.noteapplication.CustomAdapter
import lt.paulius.noteapplication.repository.Note
import lt.paulius.noteapplication.notedetailsactivity.NoteDetails
import lt.paulius.noteapplication.R
import lt.paulius.noteapplication.databinding.ActivityNotesBinding

class Notes : AppCompatActivity() {

    lateinit var adapter: CustomAdapter
    private lateinit var binding: ActivityNotesBinding
    private val activityViewModel: NotesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_notes)
        binding.notes = this

        setUpListView()

        setClickNoteDetails()
    }

    override fun onResume() {
        super.onResume()
        activityViewModel.fetchNotes()
    }

    private fun setUpListView() {
        adapter = CustomAdapter(this)
        binding.lvNoteListView.adapter = adapter

        activityViewModel.notesLiveData.observe(
            this,
            Observer { listOfNotes ->
                adapter.add(listOfNotes)
            }
        )
    }

    fun setUpOnClickListener(view: View) {
        startActivity(Intent(this, NoteDetails::class.java))
    }

    private fun setClickNoteDetails() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                activityViewModel.filterNotesByName(newText)
                return true
            }
        })

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
