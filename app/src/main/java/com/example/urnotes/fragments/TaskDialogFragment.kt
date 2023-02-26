package com.example.urnotes.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.activityViewModels
import com.example.urnotes.data.task_db.TaskEntity
import com.example.urnotes.databinding.FragmentDialogTaskBinding
import com.example.urnotes.viewmodels.NoteDBViewModel
import com.example.urnotes.viewmodels.TaskDBViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DialogTaskFragment : BottomSheetDialogFragment() {
    private lateinit var dialogTaskBinding: FragmentDialogTaskBinding
    private val taskViewModel: TaskDBViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialogTaskBinding = FragmentDialogTaskBinding.inflate(inflater)
        dialogTaskBinding.apply {
            btnSaveTask.setOnClickListener {
                if (edTask.text.toString() != "") {
                    taskViewModel.insert(
                        TaskEntity(
                            false, edTask.text.toString(),
                            "${simpleDatePicker.month + 1}.${simpleDatePicker.dayOfMonth}", 0
                        )
                    )
                    Log.d(tag, "${simpleDatePicker.month + 1}.${simpleDatePicker.dayOfMonth}")
                }
                dialog?.cancel()
            }
        }
        return dialogTaskBinding.root
    }


}