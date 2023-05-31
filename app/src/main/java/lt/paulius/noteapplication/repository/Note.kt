package lt.paulius.noteapplication.repository

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Note(
    private val _id: Int,
    private var _name: String,
    private var _details: String,
    private var _creationDate: LocalDateTime = LocalDateTime.now(),
    private var _updateDate: LocalDateTime = LocalDateTime.now()
) {

    var id = this._id
        private set

    var name: String = ""
        get(): String {
            return _name
        }
        set(value) {
            field = value
            this._name = value
            this._updateDate = LocalDateTime.now()
        }

    var details: String
        get() = _details
        set(value) {
            this._details = value
            this._updateDate = LocalDateTime.now()
        }

    val creationDate: String
        get() = formatDate(_creationDate)

    val updateDate: String
        get() = formatDate(_updateDate)

    private fun formatDate(dateTime: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        return dateTime. format(formatter)
    }
}
