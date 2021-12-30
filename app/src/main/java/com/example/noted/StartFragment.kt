package com.example.noted

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noted.Adapter.ItemAdapter
import com.example.noted.data.Datasource
import com.example.noted.databinding.FragmentStartBinding
import com.example.noted.model.CoursesViewModel

class StartFragment: Fragment() {
    // Binding object instance corresponding to the fragment_start.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var binding: FragmentStartBinding? = null
//    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    private val sharedViewModel: CoursesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.startFragment = this

        val myDataSet = Datasource().loadCourses()
        recyclerView = binding?.dataList!!
//        val gridLayoutManager: GridLayoutManager = GridLayoutManager(this,2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
//        dataList.layoutManager = gridLayoutManager
        recyclerView.adapter = context?.let { ItemAdapter(it, myDataSet) }
        recyclerView.setHasFixedSize(true)




    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}