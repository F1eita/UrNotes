package com.example.urnotes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.urnotes.viewmodels.NavViewModel
import com.example.urnotes.Note
import com.example.urnotes.viewmodels.NoteDBViewModel
import com.example.urnotes.R
import com.example.urnotes.data.note_db.NoteEntity
import com.example.urnotes.data.note_db.toNote
import com.example.urnotes.databinding.FragmentHomeBinding
import com.example.urnotes.recycler.NotesRecyclerViewAdapter


class HomeFragment : Fragment(), NotesRecyclerViewAdapter.Listener  {
    private lateinit var homeBinding: FragmentHomeBinding
    private val noteViewModel: NoteDBViewModel by activityViewModels()
    private val navViewModel: NavViewModel by activityViewModels()

    private val adapter = NotesRecyclerViewAdapter(this)

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = NavHostFragment.findNavController(this)

        homeBinding = FragmentHomeBinding.inflate(inflater)

        homeBinding.apply {
            // Navigation Bottom Bar
            bottomNavHome.setupWithNavController(navController)
            bottomNavHome.setOnNavigationItemSelectedListener { item ->
                navController.navigate(item.itemId)
                return@setOnNavigationItemSelectedListener true
            }
            // Navigate to new Note
            cardViewAddNote.setOnClickListener {
                navViewModel.setCurrentId(null)
                navController.navigate(R.id.noteFragment)
            }
        }
        loadNotes()
        return homeBinding.root
    }

    fun loadNotes(){
        homeBinding.rcViewNotes.layoutManager = LinearLayoutManager(this.context)
        homeBinding.rcViewNotes.adapter = adapter

        noteViewModel.notes.observe(viewLifecycleOwner){
                adapter.notesList = it.toArrayListNote()
        }
    }

    override fun onClick(note: Note) {
        navViewModel.setCurrentId(note.id)
        navController.navigate(R.id.noteFragment)
    }

    fun List<NoteEntity>.toArrayListNote(): ArrayList<Note>{
        val newList = ArrayList<Note>()
        for (i in this){
            newList.add(i.toNote())
        }
        return newList
    }
}