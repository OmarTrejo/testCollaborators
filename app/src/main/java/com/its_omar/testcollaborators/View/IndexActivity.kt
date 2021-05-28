package com.its_omar.testcollaborators.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.its_omar.testcollaborators.R

class IndexActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth;

    companion object {
        private const val TAG = "IndexActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        // Inicializar el auth
        auth = FirebaseAuth.getInstance()

        // Inicializa el bottom navigation
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        val navController = findNavController(R.id.nav_fragment)
        bottomNavigationView.setupWithNavController(navController)
    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) reload()
    }

    private fun reload() {
        Log.d(TAG, "reload: ")
    }
}