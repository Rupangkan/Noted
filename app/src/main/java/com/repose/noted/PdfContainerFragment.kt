package com.repose.noted

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.repose.noted.Adapter.PdfContainerAdapter
import com.repose.noted.Adapter.SemesterAdapter
import com.repose.noted.databinding.FragmentPdfcontainerBinding
import com.repose.noted.model.AppViewModel
import com.repose.noted.model.PdfContainer

class PdfContainerFragment: Fragment() {

    private var binding: FragmentPdfcontainerBinding? = null
//    private val binding get() = _binding!!

    private lateinit var dbref: StorageReference
    private lateinit var recyclerView: RecyclerView
    private lateinit var pdfArrayList: ArrayList<PdfContainer>

    private val sharedViewModel: AppViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentPdfcontainerBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.pdfContainerFragment = this

        recyclerView = binding?.dataList!!
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.setHasFixedSize(true)

        pdfArrayList = arrayListOf<PdfContainer>()

        getPdfData()

    }

    fun getPdfData() {

        dbref = FirebaseStorage.getInstance().reference.child("chemical/fifth-sem")

        dbref.listAll()
            .addOnSuccessListener { it ->
                it.items.forEach {
                    pdfArrayList.add(PdfContainer(it.name))
                    Log.d("name", it.name)
                }
            }
            .addOnFailureListener {
                // Uh-oh, an error occurred!
                Log.e("Error:", it.toString())
            }
        recyclerView.adapter = context?.let { PdfContainerAdapter( sharedViewModel, it, pdfArrayList) }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null

    }
}