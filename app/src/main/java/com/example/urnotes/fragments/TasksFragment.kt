package com.example.urnotes.fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.urnotes.*
import com.example.urnotes.data.task_db.TaskEntity
import com.example.urnotes.data.task_db.toTask
import com.example.urnotes.databinding.FragmentTasksBinding
import com.example.urnotes.recycler.NotesRecyclerViewAdapter
import com.example.urnotes.recycler.TaskOnClickListener
import com.example.urnotes.recycler.TasksRecyclerViewAdapter
import com.example.urnotes.viewmodels.NoteDBViewModel
import com.example.urnotes.viewmodels.TaskDBViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class TasksFragment : Fragment() {

    lateinit var tasksBinding: FragmentTasksBinding

    private val tasksViewModel: TaskDBViewModel by activityViewModels()

    private lateinit var adapter: TasksRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(tag, LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd")).toString())

        tasksBinding = FragmentTasksBinding.inflate(inflater)

        adapter = TasksRecyclerViewAdapter(object: TaskOnClickListener{
            override fun onTaskCheckBox(task: Task) {
                //tasksViewModel.delete(task.id)
                tasksViewModel.update(task.toTaskEntity())
            }
        })

        val navController = NavHostFragment.findNavController(this)

        tasksBinding.apply {
            bottomNavSettings.setupWithNavController(navController)
            bottomNavSettings.setOnNavigationItemSelectedListener { item ->
                navController.navigate(item.itemId)
                return@setOnNavigationItemSelectedListener true
            }

            cardViewAddTask.setOnClickListener {
                navController.navigate(R.id.dialogTaskFragment)
            }
        }

        loadTasks()
        return tasksBinding.root
    }

    fun loadTasks(){
        tasksBinding.rcViewTasks.layoutManager = LinearLayoutManager(this.context)
        tasksBinding.rcViewTasks.adapter = adapter

        tasksViewModel.tasks.observe(viewLifecycleOwner){
            adapter.tasksList = it.toArrayListTask()
        }
    }
}

fun List<TaskEntity>.toArrayListTask(): ArrayList<Task>{
    val newList = ArrayList<Task>()
    for (i in this){
        newList.add(i.toTask())
    }
    return newList
}