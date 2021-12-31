package com.repose.noted.data

import com.repose.noted.R
import com.repose.noted.model.Courses
import com.repose.noted.model.Semester

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

    fun loadSemester(): List<Semester> {
        return listOf<Semester>(
            Semester(R.string.first, R.drawable.ic_firstsemester),
            Semester(R.string.second, R.drawable.ic_secondsemester),
            Semester(R.string.third, R.drawable.ic_thirdsemester),
            Semester(R.string.fourth, R.drawable.ic_fourtsemester),
            Semester(R.string.fifth, R.drawable.ic_fifthsemester),
            Semester(R.string.sixth, R.drawable.ic_sixthsemester),
            Semester(R.string.seventh, R.drawable.ic_seventhsemester),
            Semester(R.string.eigth, R.drawable.ic_eightsemester)
        )
    }
}