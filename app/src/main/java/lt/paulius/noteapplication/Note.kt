package lt.paulius.noteapplication

data class Note(
    val id: Int,
    var name: String,
    var details: String,
    private val creationDate: String,
    private var updateDate: String
)