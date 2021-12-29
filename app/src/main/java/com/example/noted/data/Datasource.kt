package com.example.noted.data

import com.example.noted.R
import com.example.noted.model.Courses

class Datasource() {

    fun loadCourses(): List<Courses> {
        return listOf<Courses>(
            Courses(R.string.computer, R.drawable.ic_computer),
            Courses(R.string.chemical, R.drawable.ic_chemical),
            Courses(R.string.mechanical, R.drawable.ic_mechanical),
            Courses(R.string.civil, R.drawable.ic_civil),
            Courses(R.string.instrumental, R.drawable.ic_instrumental),
            Courses(R.string.industrial, R.drawable.ic_industrial),
            Courses(R.string.electrical, R.drawable.ic_electrical),
            Courses(R.string.electronics, R.drawable.ic_electronics)

        )
    }
}