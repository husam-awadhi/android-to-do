package com.husam.to_do.fragments.details

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.husam.to_do.R
import com.husam.to_do.databinding.FragmentDetailsBinding

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_details, container, false)
        _binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_bar_details_menu, menu)
    }

}