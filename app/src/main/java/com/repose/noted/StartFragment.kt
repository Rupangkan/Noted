package com.repose.noted

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.repose.noted.Adapter.ItemAdapter
import com.repose.noted.data.Datasource
import com.repose.noted.databinding.FragmentStartBinding
import com.repose.noted.model.AppViewModel

class StartFragment: Fragment() {
    // Binding object instance corresponding to the fragment_start.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var binding: FragmentStartBinding? = null
//    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private val sharedViewModel: AppViewModel by activityViewModels()


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
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = context?.let { ItemAdapter(sharedViewModel ,it, myDataSet) }
        recyclerView.setHasFixedSize(true)

        binding!!.floatingActionButton.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToStarredFragment()
            this.findNavController().navigate(action)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}