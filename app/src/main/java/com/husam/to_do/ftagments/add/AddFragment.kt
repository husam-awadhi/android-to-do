package com.husam.to_do.ftagments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.husam.to_do.R

/**
     * A simple [Fragment] subclass.
     * Use the [AddFragment.newInstance] factory method to
     * create an instance of this fragment.
     */
    class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }
}