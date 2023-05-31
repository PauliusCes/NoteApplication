package lt.paulius.noteapplication.notesactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lt.paulius.noteapplication.repository.Note
import lt.paulius.noteapplication.repository.NoteRepository

class NotesViewModel : ViewModel() {

    private val _notesLiveData = MutableLiveData<List<Note>>(mutableListOf())
    val notesLiveData: LiveData<List<Note>>
        get() = _notesLiveData

    fun fetchNotes() {
        if (_notesLiveData.value == null || _notesLiveData.value?.isEmpty() == true) {
            NoteRepository.instance.addDummyListOfNotes()
        }
        _notesLiveData.value = NoteRepository.instance.notes
    }

    fun filterNotesByName(name: String) {
        val allNotes = NoteRepository.instance.notes
        val filteredNotes = allNotes.filter { note ->
            note.name.contains(name, ignoreCase = true)
        }
        _notesLiveData.value = filteredNotes
    }
}
