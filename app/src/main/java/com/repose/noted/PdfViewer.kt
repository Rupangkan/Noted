package com.repose.noted

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.github.barteksc.pdfviewer.PDFView
import com.google.android.material.appbar.AppBarLayout
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.repose.noted.databinding.ActivityPdfviewerBinding
import com.repose.noted.model.AppViewModel
import android.content.res.Resources
import java.text.Format


private const val name = "question-papers"

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

class PdfViewer: AppCompatActivity() {

//    private lateinit var binding: ActivityPdfviewerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityPdfviewerBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_pdfviewer)


        var bundle :Bundle ?= intent.extras
        val course = bundle!!.getString("Course")
        val semester = bundle!!.getString("Semester")
        val pdfName = bundle!!.getString("PDF")

        Log.d("Course", course.toString())
        Log.d("Semester", semester.toString())
        Log.d("PDF", pdfName.toString())

        val temp: CharSequence = pdfName.toString()

//        getString(R.string.pdfActivityLabel, pdfName.toString())
//        val text: String = String.format(getString(R.string.pdfActivityLabel), pdfName);
//        val tempId = String.format(getString(R.string.pdfActivityLabel), pdfName)
        setTitle(temp)

        loadPDFDetails(course, semester, pdfName)
        val text = resources.getText((R.string.pdfActivityLabel), pdfName.toString())
        Log.d("resource", text.toString())

    }

    private fun loadPDFDetails(course: String?, semester: String?, pdfName: String?) {
//        val ref = FirebaseStorage.getInstance().reference.child("$course/$semester/$name/$pdfName")
        val course = COURSES[course]
        val semester = SEMESTER[semester]
        val storageRef =  FirebaseStorage.getInstance().reference
        var pdfRef = storageRef.child("$course/$semester/$name/$pdfName")
        val url = storageRef.child("$course/$semester/$name/$pdfName").toString()

        val pdfView = findViewById<PDFView>(R.id.pdfView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        Log.d("url", url)
//        binding.progressBar.visibility = View.VISIBLE
        val ref = FirebaseStorage.getInstance().getReferenceFromUrl("gs://noted-app-b0c56.appspot.com/$course/$semester/$name/$pdfName")
        pdfRef.getBytes(50000000)
            .addOnSuccessListener { bytes ->
                Log.d("Success", bytes.toString())
                pdfView.fromBytes(bytes)
                    .swipeHorizontal(false)
//                    .onPageChange{ page, pageCount ->
//
//                    }
                    .onError {
                        Log.d("Error", "Error")
                        Toast.makeText(this, "Error loading pdf", Toast.LENGTH_SHORT)
                    }
//                    .enableAnnotationRendering(true)
                    .onPageError { page, t ->
                        Log.d("Error", "Page error")
                        Toast.makeText(this, "Error loading page", Toast.LENGTH_SHORT)

                    }
                    .load()

                progressBar.visibility = View.GONE

            }
            .addOnFailureListener {
                Log.d("Error", "Loading pdf failed")
                progressBar.visibility = View.GONE
            }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}