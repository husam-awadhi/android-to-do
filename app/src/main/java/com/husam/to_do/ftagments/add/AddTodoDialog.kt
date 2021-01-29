package com.husam.to_do.ftagments.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.husam.to_do.R
import com.husam.to_do.models.add.AddViewModel
import kotlinx.android.synthetic.main.fragment_add_dialog.view.*

class AddTodoDialog : DialogFragment() {

    companion object {

        const val TAG = "DialogWithData"

    }

    private lateinit var viewModel: AddViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.Theme_MaterialComponents_Dialog)
        viewModel = ViewModelProvider(requireActivity()).get(AddViewModel::class.java)
        setupClickListeners(view)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setupClickListeners(view: View) {
        view.btnSubmit.setOnClickListener {
            val taskText : String = view.todo_task_text.text.toString()
            val priorityText : String = this.getChipText(view)
            if (taskText.isEmpty() || priorityText.isEmpty()) {
                Toast.makeText(context,getString(R.string.new_task_must_fill_all),Toast.LENGTH_SHORT).show()
            }else{
                dismiss()
                viewModel.sendTask(taskText, priorityText)
            }
        }
    }


    private fun getChipText(view: View) : String {
        return view.findViewById<Chip>(view.priority_chips.checkedChipId).text.toString()
    }

}