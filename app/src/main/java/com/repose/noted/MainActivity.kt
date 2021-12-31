package com.repose.noted

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Retrieve NavController from the NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Set up the action bar for use with the NavController
        setupActionBarWithNavController(navController)

//        val myDataSet = Datasource().loadCourses()
//        val dataList = findViewById<RecyclerView>(R.id.dataList)
//        val gridLayoutManager: GridLayoutManager = GridLayoutManager(this,2, GridLayoutManager.VERTICAL, false)
//        dataList.layoutManager = gridLayoutManager
//        dataList.adapter = ItemAdapter(this, myDataSet)
//        dataList.setHasFixedSize(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}