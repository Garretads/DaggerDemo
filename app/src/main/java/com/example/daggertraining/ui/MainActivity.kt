package com.example.daggertraining.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.example.daggertraining.R


class MainActivity : AppCompatActivity() {

    private val navController: NavController? by lazy { findNavController() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        navController?.let { setupActionBarWithNavController(this, it) }
    }

    override fun onSupportNavigateUp(): Boolean = navController?.popBackStack() ?: false


    private fun findNavController(): NavController? {
        val navHostFragment = (this as? MainActivity)
            ?.supportFragmentManager
            ?.findFragmentById(R.id.currentNavHostId) as? NavHostFragment
        return navHostFragment?.navController
    }

}
