package lt.paulius.noteapplication

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Note(
    var id: Int = 0,
    var name: String,
    var details: String,
    val creationDate: LocalDateTime = LocalDateTime.now(),
    private var updateDate: LocalDateTime = LocalDateTime.now()
) {
    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    init {
        id = ++noteId
    }

    fun getFormattedCreationDate(): String {
        return creationDate.format(dateFormatter)
    }

    fun getFormattedUpdateDate(): String {
        return updateDate.format(dateFormatter)
    }

    companion object {
        private var noteId = 0
    }
}