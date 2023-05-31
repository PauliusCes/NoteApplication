package lt.paulius.noteapplication.notedetailsactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lt.paulius.noteapplication.repository.Note
import lt.paulius.noteapplication.repository.NoteRepository

class NoteDetailsViewModel : ViewModel() {

    private val _noteLiveData = MutableLiveData<Note>()
    val noteLiveData: LiveData<Note>
        get() = _noteLiveData

    fun fetchNote(noteId: Int) {
        if (_noteLiveData.value == null) {
            if (noteId > 0) {
                _noteLiveData.value = NoteRepository.instance.getNote(noteId)
            } else {
                _noteLiveData.value = Note(-1, "", "")
            }
        }
    }

    fun saveNote(note: Note) {
        if (note != null) {
            if (note.id > 0) {
                NoteRepository.instance.updateNote(note)
            } else {
                NoteRepository.instance.addNote(note)
            }
        }
    }
}
