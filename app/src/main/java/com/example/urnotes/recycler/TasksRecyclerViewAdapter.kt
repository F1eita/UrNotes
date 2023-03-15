package com.example.urnotes.recycler

import android.content.Context
import android.util.TypedValue
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.urnotes.DateConverter
import com.example.urnotes.R
import com.example.urnotes.Task
import com.example.urnotes.databinding.ItemTaskBinding

interface TaskOnClickListener{
    fun onTaskCheckBox(task: Task)
}
class TasksRecyclerViewAdapter(val context: Context, private val listener: TaskOnClickListener): RecyclerView
                .Adapter<TasksRecyclerViewAdapter.TasksRecyclerViewHolder>(), View.OnClickListener,
                                                            ActionMode.Callback{

    var tasksList = ArrayList<Task>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    lateinit var taskBinding: ItemTaskBinding

    private var multiSelect = false
    private var selectedItems = ArrayList<Task>()

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
            tvDate.text = DateConverter.ConvertDate(task.date, context = context)

            when(position){
                0 -> tvDate.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.resources.getDimension(
                    R.dimen.text_date))
                else -> {
                    if (task.date == tasksList[position - 1].date){
                        tvDate.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.resources.getDimension(
                            R.dimen.no_text))
                    }
                    else{
                        tvDate.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.resources.getDimension(
                            R.dimen.text_date))
                    }
                }
            }
        }
    }

    // listener item
    override fun onClick(v: View) {

    }

    // action bar
    override fun onCreateActionMode(p0: ActionMode?, p1: Menu?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onActionItemClicked(p0: ActionMode?, p1: MenuItem?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onDestroyActionMode(p0: ActionMode?) {
        TODO("Not yet implemented")
    }

    override fun onPrepareActionMode(p0: ActionMode?, p1: Menu?): Boolean {
        TODO("Not yet implemented")
    }
}