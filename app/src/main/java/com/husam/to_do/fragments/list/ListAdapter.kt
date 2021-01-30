package com.husam.to_do.fragments.list


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.husam.to_do.data.Task
import com.husam.to_do.databinding.TaskLayoutBinding
import com.husam.to_do.models.SharedModelView
import java.util.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.TaskViewHolder>() {

    var taskList = emptyList<Task>()
    private var _binding: TaskLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        _binding = TaskLayoutBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) =
        holder.bind(taskList[position])


    override fun getItemCount(): Int {
        return taskList.size
    }

    fun setData(taskList: List<Task>) {
        this.taskList = taskList
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(bind: TaskLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        private val sharedModelView = SharedModelView()

        fun bind(item: Task) {
            with(binding) {
                taskContent.text = item.content
                taskPriorityMark.setColorFilter(
                    ContextCompat.getColor(
                        root.context,
                        sharedModelView.getPriorityColor(item.priority)
                    )
                )
                taskStatus.text = item.status.toUpperCase(Locale.ROOT)
                cardBackground.setOnClickListener {
                    val action = HomeFragmentDirections.actionNavigationHomeToDetailsFragment(item)
                    findNavController(binding.root)
                        .navigate(action)
                }
            }
        }
    }

}