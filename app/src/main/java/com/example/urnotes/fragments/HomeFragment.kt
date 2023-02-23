package com.example.urnotes.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.urnotes.NavViewModel
import com.example.urnotes.Note
import com.example.urnotes.NoteDBViewModel
import com.example.urnotes.R
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
                adapter.clearAdapter()
                for (i in it){
                    val newNote = Note(i.title, i.text, i.time, i.date, i.id)
                    Log.d(TAG, "loadNotes: ${i.id}, ${i.title}")
                    adapter.addNote(newNote)
                }
        }
    }

    override fun onClick(note: Note) {
        navViewModel.setCurrentId(note.id)
        navController.navigate(R.id.noteFragment)
    }

}