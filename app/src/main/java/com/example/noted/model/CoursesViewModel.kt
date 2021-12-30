package com.example.noted.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

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