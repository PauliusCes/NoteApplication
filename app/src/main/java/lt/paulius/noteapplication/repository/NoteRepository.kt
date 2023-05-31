package lt.paulius.noteapplication.repository

class NoteRepository {

    val notes = mutableListOf<Note>()

    fun getNote(id: Int) = notes.find { it.id == id }

    fun addNote(note: Note) {
        var newId = 1

        if (notes.isNotEmpty()) {
            newId = notes.maxBy { it.id }.id.inc()
        }
        val newNote = Note(_id = newId, note.name, note.details)
        notes.add(newNote)
    }

    fun updateNote(note: Note?) {
        if (note != null) {
            val index = notes.indexOfFirst { it.id == note.id }
            if (index >= 0) {
                notes[index] = note
            }
        }
    }

    fun addDummyListOfNotes() {
        notes.addAll(generateListOfNotes())
    }

    private fun generateListOfNotes(): List<Note> {
        val list = mutableListOf<Note>()

        val note1 = Note(
            1,
            "Buy stuff",
            "Have to buy milk",
        )
        val note2 = Note(
            2,
            "Diary",
            "First entry"
        )

        list.add(note1)
        list.add(note2)

        return list
    }

    companion object {
        val instance: NoteRepository = NoteRepository()
    }
}
