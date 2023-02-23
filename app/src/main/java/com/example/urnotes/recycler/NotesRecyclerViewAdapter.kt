package com.example.urnotes.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.urnotes.Note
import com.example.urnotes.R
import com.example.urnotes.databinding.ItemNoteBinding

class NotesRecyclerViewAdapter(val listener: Listener): RecyclerView
                    .Adapter<NotesRecyclerViewAdapter.NotesRecyclerViewHolder>() {

    private val notesList = ArrayList<Note>()

    class NotesRecyclerViewHolder(item: View): RecyclerView.ViewHolder(item){
        val binding = ItemNoteBinding.bind(item)
        fun bind(note: Note, listener: Listener){
            binding.apply {
                tvTitle.text = note.title
                tvItemTimeDate.text = "${note.date}   ${note.time.slice(0..4)}"
                tvLittleText.text = note.text
            }
            itemView.setOnClickListener{
                listener.onClick(note)
            }
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesRecyclerViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NotesRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesRecyclerViewHolder, position: Int) {
        holder.bind(notesList[position], listener)
    }

    fun addNote(note: Note){
        notesList.add(note)
        notifyDataSetChanged()
    }

    fun clearAdapter(){
        notesList.clear()
    }


    interface Listener{
        fun onClick(contact: Note)
    }
}