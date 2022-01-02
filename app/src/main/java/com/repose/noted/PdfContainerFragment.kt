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
import com.repose.noted.databinding.FragmentPdfcontainerBinding
import com.repose.noted.model.AppViewModel
import com.repose.noted.model.PdfContainer

private const val name = "question-papers"

class PdfContainerFragment: Fragment() {

    private var binding: FragmentPdfcontainerBinding? = null
//    private val binding get() = _binding!!

    private lateinit var dbref: StorageReference
    private lateinit var recyclerView: RecyclerView
    private lateinit var pdfArrayList: ArrayList<PdfContainer>

//    private val SEMESTER = mapOf(
//        getString(R.string.first) to "first-sem",
//        getString(R.string.second) to "second-sem",
//        getString(R.string.third) to "third-sem",
//        getString(R.string.fourth) to "fourth-sem",
//        getString(R.string.fifth) to "fifth-sem",
//        getString(R.string.sixth) to "sixth-sem",
//        getString(R.string.seventh) to "seventh-sem",
//        getString(R.string.eigth) to "eight-sem",
//    )

    private val SEMESTER = mapOf(
        "1st Semester" to "first-sem",
        "2nd Semester" to "second-sem",
        "3rd Semester" to "third-sem",
        "4th Semester" to "fourth-sem",
        "5th Semester" to "fifth-sem",
        "6th Semester" to "sixth-sem",
        "7th Semester" to "seventh-sem",
        "8th Semester" to "eight-sem",
    )
//    private val COURSES = mapOf(
//        getString(R.string.civil) to "civil",
//        getString(R.string.computer) to "computer",
//        getString(R.string.instrumental) to "instrumental",
//        getString(R.string.industrial) to "industrial",
//        getString(R.string.chemical) to "chemical",
//        getString(R.string.mechanical) to "mechanical",
//        getString(R.string.electrical) to "electrical",
//        getString(R.string.electronics) to "electronics",
//    )

    private val COURSES = mapOf(
        "Civil" to "civil",
        "Computer" to "computer",
        "Instrumental" to "instrumental",
        "Industrial" to "industrial",
        "Chemical" to "chemical",
        "Mechanical" to "mechanical",
        "Electrical" to "electrical",
        "Electronics" to "electronics",
    )


//    private val courses = Datasource().loadCourses()
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

    private fun getPdfData() {
        val userSemChoice = sharedViewModel.semesterSelected.value!!
        val userCourseChoice = sharedViewModel.coursesSelected.value!!
        var tempCourse = ""
        var tempSem = ""
        if(userSemChoice.equals(getString(R.string.first)) || userSemChoice.equals(getString(R.string.second))) {
            tempSem = userSemChoice
            dbref = FirebaseStorage.getInstance().reference.child("$tempSem/$name")
        }else {
            tempCourse = COURSES[userCourseChoice].toString()
            Log.d("tempCourse: ", tempCourse)
            Log.d("userSemChoice: ", userSemChoice)
            tempSem = SEMESTER[userSemChoice].toString()
            Log.d("tempSem: ", tempSem)

            dbref = FirebaseStorage.getInstance().reference.child("$tempCourse/$tempSem/$name")

        }

        dbref.listAll()
            .addOnSuccessListener { it ->
                it.items.forEach {
                    var pdf = PdfContainer(it.name)
                    pdfArrayList.add(pdf)
                    recyclerView.adapter = context?.let { PdfContainerAdapter( sharedViewModel, it, pdfArrayList ) }
                    Log.d("name", it.name)
                }
            }
            .addOnFailureListener {
                // Uh-oh, an error occurred!
                Log.e("Error:", it.toString())
            }
        Log.d("pdfArrayList:", pdfArrayList.toString())
//        recyclerView.adapter = context?.let { PdfContainerAdapter( sharedViewModel, it, pdfArrayList ) }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null

    }
}