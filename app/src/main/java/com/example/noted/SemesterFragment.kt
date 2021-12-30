package com.example.noted

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noted.Adapter.ItemAdapter
import com.example.noted.Adapter.SemesterAdapter
import com.example.noted.data.Datasource
import com.example.noted.databinding.FragmentSemesterBinding
import com.example.noted.databinding.FragmentStartBinding
import com.example.noted.model.CoursesViewModel

class SemesterFragment: Fragment() {
    private var binding: FragmentSemesterBinding? = null
//    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentSemesterBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.semesterFragment = this

        val myDataSet = Datasource().loadSemester()
        recyclerView = binding?.dataList!!
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = context?.let { SemesterAdapter(it, myDataSet) }
        recyclerView.setHasFixedSize(true)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}