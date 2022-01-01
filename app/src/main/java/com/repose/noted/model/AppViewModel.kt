package com.repose.noted.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppViewModel : ViewModel() {

    // Courses Selected
    private val _courseSelected = MutableLiveData<String>("")
    val coursesSelected: LiveData<String> = _courseSelected

    private val _semesterSelected = MutableLiveData<String>("")
    val semesterSelected: LiveData<String> = _semesterSelected

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

    private fun resetOrder() {
        _courseSelected.value = ""
        _semesterSelected.value = ""
    }

}