package com.example.urnotes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.urnotes.viewmodels.NavViewModel
import com.example.urnotes.Note
import com.example.urnotes.viewmodels.NoteDBViewModel
import com.example.urnotes.R
import com.example.urnotes.data.note_db.NoteEntity
import com.example.urnotes.databinding.FragmentNoteBinding
import java.time.LocalDate
import java.time.LocalTime


class NoteFragment: Fragment(){
    private lateinit var noteBinding: FragmentNoteBinding
    private val noteViewModel: NoteDBViewModel by activityViewModels()
    private val navViewModel: NavViewModel by activityViewModels()

    private var currId: Int? = null
    private lateinit var note: Note
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        noteBinding = FragmentNoteBinding.inflate(inflater)

        navController = NavHostFragment.findNavController(this)

        navViewModel.id.observe(viewLifecycleOwner) {
            currId = it
            if (currId != null){
                noteViewModel.getNoteById(currId!!).observe(viewLifecycleOwner, Observer {
                    note = Note(it.title, it.text, it.time, it.date, it.id)
                    noteBinding.apply{
                        edTitle.setText(note.title)
                        edTextNote.setText(note.text)
                        tvTimeDate.text = "${it.date}   ${it.time.slice(0..4)}"
                    } })

            }
            else{
                noteBinding.apply {
                    btnReturn.visibility = INVISIBLE
                    btnDelete.visibility = INVISIBLE
                }
            }
        }

        return noteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteBinding.apply {
            btnSave.setOnClickListener{
                navViewModel.id.observe(viewLifecycleOwner){
                    note = Note(edTitle.text.toString(), edTextNote.text.toString(),
                        LocalTime.now().toString(), LocalDate.now().toString(), 0)
                    if (it != null){
                        noteViewModel.update(NoteEntity(note.title, note.text, note.time, note.date, it))
                    }
                    else{
                        noteViewModel.insert(NoteEntity(note.title, note.text, note.time, note.date, 0))
                        navController.navigate(R.id.homeFragment)
                    }
                }
            }
            btnReturn.setOnClickListener {
                navViewModel.id.observe(viewLifecycleOwner){
                    noteViewModel.getNoteById(it!!).observe(viewLifecycleOwner){
                        note = Note(it.title, it.text, it.time, it.date, it.id)
                        noteBinding.apply{
                            edTitle.setText(note.title)
                            edTextNote.setText(note.text)
                        }
                    }
                }
            }
            btnDelete.setOnClickListener {
                navViewModel.id.observe(viewLifecycleOwner) {
                    if (it != null){
                        noteViewModel.delete(it)
                    }
                }
                navController.navigate(R.id.homeFragment)
            }
        }
    }

}
