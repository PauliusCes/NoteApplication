package lt.paulius.noteapplication.notedetailsactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import lt.paulius.noteapplication.repository.Note
import lt.paulius.noteapplication.R
import lt.paulius.noteapplication.databinding.ActivityNoteDetailsBinding
import lt.paulius.noteapplication.notesactivity.Notes

class NoteDetails : AppCompatActivity() {

    private lateinit var binding: ActivityNoteDetailsBinding
    private val activityViewModel: NoteDetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_note_details)
        binding.noteDetails = this

        activityViewModel.noteLiveData.observe(
            this,
            Observer { note ->
                binding.note = note
            }
        )
        activityViewModel.fetchNote(getIntentExtra())
    }

    private fun getIntentExtra() = intent.getIntExtra(Notes.NOTE_INTENT_ID, -1)

    fun onClickBackButton(view: View) {
        finish()
    }

    fun onClickSaveButton(view: View) {
        activityViewModel.saveNote(binding.note as Note)
        finish()
    }
}
