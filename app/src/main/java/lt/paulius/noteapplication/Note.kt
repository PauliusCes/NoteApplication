package lt.paulius.noteapplication

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Note(
    private var _id: Int = 0,
    private var _name: String,
    private var _details: String,
    private var _creationDate: LocalDateTime = LocalDateTime.now(),
    private var _updateDate: LocalDateTime = LocalDateTime.now()
) {
    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

//    init {
//        id = ++noteId
//    }

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
        get(): String {
            return _details
        }
        set(value) {
            this._details = value
            this._updateDate = LocalDateTime.now()
        }

    var creationDate = this._creationDate
        private set

    var updateDate: LocalDateTime
        get() = this._updateDate
        private set(value) {
            this._updateDate = value
        }

//    fun getFormattedCreationDate(): String {
//        return creationDate.format(dateFormatter)
//    }
//
//    fun getFormattedUpdateDate(): String {
//        return updateDate.format(dateFormatter)
//    }

//    companion object {
//        private var noteId = 0
//    }
}