package lt.paulius.noteapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import lt.paulius.noteapplication.databinding.NoteBinding

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
        var view = convertView
        var binding: NoteBinding

        if(view == null) {
            binding = NoteBinding.inflate(inflater, parent, false)
            view = binding.root
            view.tag = binding
        } else {
            binding = view.tag as NoteBinding
        }

        binding.tvNoteId.text = list[position].id.toString()
        binding.tvName.text = list[position].name
        binding.tvNoteDetails.text = list[position].details
        binding.tvCreationDate.text = list[position].creationDate.toString()
        binding.tvUpdateDate.text = list[position].updateDate.toString()

        return view
    }

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = list.size

}