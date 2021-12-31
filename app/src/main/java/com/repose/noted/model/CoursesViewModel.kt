package com.repose.noted.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CoursesViewModel : ViewModel() {

    // Courses Selected
    private val _courseSelected = MutableLiveData<String>()
    val coursesSelected: LiveData<String> = _courseSelected


    init {
        // Set initial values for the course
        resetOrder()
    }

    private fun resetOrder() {
        _courseSelected.value = ""
    }

}