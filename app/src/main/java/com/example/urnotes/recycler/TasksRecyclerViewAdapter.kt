package com.example.urnotes.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.urnotes.DateConverter
import com.example.urnotes.Task
import com.example.urnotes.databinding.ItemTaskBinding

interface TaskOnClickListener{
    fun onTaskCheckBox(task: Task)
}
class TasksRecyclerViewAdapter(private val listener: TaskOnClickListener): RecyclerView
                .Adapter<TasksRecyclerViewAdapter.TasksRecyclerViewHolder>()  {

    var tasksList = ArrayList<Task>()
        set(value){
            field = value
            notifyDataSetChanged()
        }
    lateinit var taskBinding: ItemTaskBinding

    class TasksRecyclerViewHolder(val binding: ItemTaskBinding): RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return tasksList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksRecyclerViewHolder {
        taskBinding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TasksRecyclerViewHolder(taskBinding)
    }

    override fun onBindViewHolder(holder: TasksRecyclerViewHolder, position: Int) {
        val task = tasksList[position]
        with(holder.binding) {
            checkBoxIsDone.isChecked = task.isDone
            checkBoxIsDone.setOnClickListener {
                task.isDone = !task.isDone
                listener.onTaskCheckBox(task)
            }
            tvTask.text = task.text
            when(position){
                0 -> tvDate.text = DateConverter.ConvertDate(task.date)
                else -> {
                    if (task.date == tasksList[position - 1].date) {
                        tvDate.text = ""
                    } else {
                        tvDate.text = DateConverter.ConvertDate(task.date)
                    }
                }
            }
        }
    }



}