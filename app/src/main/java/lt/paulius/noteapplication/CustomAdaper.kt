package lt.paulius.noteapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CustomAdaper(context: Context) : BaseAdapter() {

    private val inflater = LayoutInflater.from(context)
    private val list = mutableListOf<Note>()

    fun add(vararg note: Note) {
        list.addAll(note)
        notifyDataSetChanged()
    }

    fun add(notes: List<Note>) {
        list.addAll(notes)
        notifyDataSetChanged()
    }

    fun update(index: Int, note: Note) {
        list[index] = note
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    fun remove(vararg note: Note) {
        list.removeAll(note)
        notifyDataSetChanged()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: inflater.inflate(R.layout.note, parent, false)

        view.findViewById<TextView>(R.id.tvNoteId).text = list[position].id.toString()
        view.findViewById<TextView>(R.id.tvName).text = list[position].name
        view.findViewById<TextView>(R.id.tvNoteDetails).text = list[position].details
        view.findViewById<TextView>(R.id.tvCreationDate).text =
            list[position].getFormattedCreationDate()

        return view
    }

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = list.size

}