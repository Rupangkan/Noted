package com.repose.noted.model

import android.content.ClipData
import androidx.lifecycle.*
import com.repose.noted.data.Starred
import com.repose.noted.data.StarredDao
import kotlinx.coroutines.launch

class AppViewModel() : ViewModel() {

    // Courses Selected
    private val _courseSelected = MutableLiveData<String>("")
    val coursesSelected: LiveData<String> = _courseSelected

    private val _semesterSelected = MutableLiveData<String>("")
    val semesterSelected: LiveData<String> = _semesterSelected

    private val _pdfName = MutableLiveData<String>("")
    val pdfName: LiveData<String> = _pdfName

    private val _path = MutableLiveData<String>("")
    val path: LiveData<String> = _path

    init {
        // Set initial values for the course
        resetOrder()
    }



    fun setCourse(course: String) {
        _courseSelected.value = course
    }

    fun setSemester(semester: String) {
        _semesterSelected.value = semester
    }

    fun setPdfName(name: String) {
        _pdfName.value = name
    }

    fun setPath(name: String) {
        _path.value = name
    }

    private fun resetOrder() {
        _courseSelected.value = ""
        _semesterSelected.value = ""
        _pdfName.value = ""
        _path.value = ""
    }

}

