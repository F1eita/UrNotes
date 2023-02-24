package com.example.urnotes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.urnotes.databinding.FragmentTasksBinding


class TasksFragment : Fragment() {

    lateinit var tasksBinding: FragmentTasksBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        tasksBinding = FragmentTasksBinding.inflate(inflater)

        val navController = NavHostFragment.findNavController(this)

        tasksBinding.apply {
            bottomNavSettings.setupWithNavController(navController)
            bottomNavSettings.setOnNavigationItemSelectedListener { item ->
                navController.navigate(item.itemId)
                return@setOnNavigationItemSelectedListener true
            }
        }
        return tasksBinding.root
    }

}