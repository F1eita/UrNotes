package com.example.urnotes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.urnotes.R
import com.example.urnotes.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    lateinit var settingsBinding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        settingsBinding = FragmentSettingsBinding.inflate(inflater)

        val navController = NavHostFragment.findNavController(this)

        settingsBinding.apply {
            bottomNavSettings.setupWithNavController(navController)
            bottomNavSettings.setOnNavigationItemSelectedListener { item ->
                navController.navigate(item.itemId)
                return@setOnNavigationItemSelectedListener true
            }
        }
        return settingsBinding.root
    }

}