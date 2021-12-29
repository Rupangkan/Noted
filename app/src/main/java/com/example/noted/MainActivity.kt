package com.example.noted

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noted.Adapter.ItemAdapter
import com.example.noted.data.Datasource

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myDataSet = Datasource().loadCourses()
        val dataList = findViewById<RecyclerView>(R.id.dataList)
        val gridLayoutManager: GridLayoutManager = GridLayoutManager(this,2, GridLayoutManager.VERTICAL, false)
        dataList.layoutManager = gridLayoutManager
        dataList.adapter = ItemAdapter(this, myDataSet)
        dataList.setHasFixedSize(true)


    }
}