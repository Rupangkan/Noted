package com.repose.noted

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.repose.noted.Adapter.SemesterAdapter
import com.repose.noted.Adapter.StarredAdapter
import com.repose.noted.data.Datasource
import com.repose.noted.data.StarredApplication
import com.repose.noted.data.StarredRoomDatabase
import com.repose.noted.databinding.FragmentPdfcontainerBinding
import com.repose.noted.databinding.FragmentStarredBinding
import com.repose.noted.model.AppViewModel
import com.repose.noted.model.RoomViewModel
import com.repose.noted.model.RoomViewModelFactory

class StarredFragment : Fragment() {

//    private val viewModel: RoomViewModel by activityViewModels {
//        RoomViewModelFactory(
//            (activity?.application as StarredApplication).database.starredDao()
//        )
//    }
    private val sharedViewModel: AppViewModel by activityViewModels()

    private val viewModel: RoomViewModel by activityViewModels {
        RoomViewModelFactory(
            (activity?.application as StarredApplication).database.starredDao()
        )
    }


    private lateinit var recyclerView: RecyclerView

    private var _binding: FragmentStarredBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStarredBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.starredFragment = this

        val myDataSet = Datasource().loadSemester()
        recyclerView = binding?.dataList!!
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        val adapter = context?.let { StarredAdapter( sharedViewModel, it ) }

        viewModel.allItems.observe(this.viewLifecycleOwner) { items ->
            items.let {
                Log.d("ListFromFragment", items.toString())
                adapter?.submitList(it)
                recyclerView.adapter = adapter
            }
        }

        recyclerView.setHasFixedSize(true)

        binding!!.floatingActionButton.setOnClickListener {
            val action = StarredFragmentDirections.actionStarredFragmentToStartFragment()
            this.findNavController().navigate(action)
        }
    }
}