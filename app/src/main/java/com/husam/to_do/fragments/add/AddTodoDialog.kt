package com.husam.to_do.fragments.add

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.husam.to_do.R
import com.husam.to_do.cruds.Task
import com.husam.to_do.databinding.FragmentAddDialogBinding
import com.husam.to_do.models.SharedModelView
import com.husam.to_do.models.TaskViewModel

class AddTodoDialog : DialogFragment() {

    companion object {

        const val TAG = "DialogWithData"

    }

    private lateinit var viewModel: TaskViewModel
    private val sharedModelView: SharedModelView by viewModels()
    private var _binding: FragmentAddDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.Theme_MaterialComponents_Dialog)
        viewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
        setupClickListeners()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setupClickListeners() {
        binding.btnSubmit.setOnClickListener {
            val taskText: String = binding.todoTaskText.text.toString()
            val priorityText: String = this.getChipText()
            if (taskText.isEmpty() || priorityText.isEmpty()) {
                Toast.makeText(
                    context,
                    getString(R.string.new_task_must_fill_all),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
//                viewModel.sendTask(taskText, priorityText)
                val task = com.husam.to_do.data.Task()

                task.content = sharedModelView.parseContent(taskText)
                task.priority = sharedModelView.parsePriority(priorityText)
                Task().add(task) //insert into Task
                dismiss()
            }
        }
    }


    private fun getChipText(): String {
        return binding.priorityChips.children
            .toList()
            .filter { (it as Chip).isChecked }
            .joinToString { (it as Chip).text }

    }

    override fun onDismiss(dialog: DialogInterface) {
        _binding = null
        super.onDismiss(dialog)
    }

}