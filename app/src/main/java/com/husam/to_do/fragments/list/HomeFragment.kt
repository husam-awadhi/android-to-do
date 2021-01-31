package com.husam.to_do.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.husam.to_do.adapters.ListAdapter
import com.husam.to_do.data.Task
import com.husam.to_do.databinding.FragmentHomeBinding
import com.husam.to_do.fragments.update.UpdateFragment
import com.husam.to_do.models.TaskViewModel
import io.realm.RealmResults


/**
 * A simple [Fragment] subclass.
 * Use the [UpdateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private val taskViewModel: TaskViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val adapter: ListAdapter by lazy {
        ListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        val recyclerView: RecyclerView = binding.taskRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        var data: RealmResults<Task>? = null
        taskViewModel.getAllData()?.let {
            data = it
            adapter.setData(it)
            taskViewModel.databaseIsEmpty(data!!)
        }

        data?.addChangeListener { task ->
            // Task Object listener
            adapter.setData(task)
            taskViewModel.databaseIsEmpty(task)
        }

        taskViewModel.noTasks.observe(viewLifecycleOwner, {
            //to show the no data found image and text in case of no stored
            //tasks.
            updateNoDataView(it)
        })

        return binding.root
    }

    private fun updateNoDataView(noTasks: Boolean) {
        val status: Int = if (noTasks) View.VISIBLE else View.INVISIBLE

        binding.noDataImg.visibility = status
        binding.noDataTxt.visibility = status
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
