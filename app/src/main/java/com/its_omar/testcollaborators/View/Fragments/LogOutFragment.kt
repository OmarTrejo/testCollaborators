package com.its_omar.testcollaborators.View.Fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.its_omar.testcollaborators.LoginActivity
import com.its_omar.testcollaborators.R

class LogOutFragment : Fragment() {

    private lateinit var auth : FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences
    private var preferences = "colaboradores_preferences"

    companion object
    {
        private const val TAG = "LogOutFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = inflater.inflate(R.layout.fragment_log_out, container, false)

        // Inicialize auth
        auth = FirebaseAuth.getInstance()

        // Inicialize Shared preferences
        sharedPreferences = requireActivity().getSharedPreferences(preferences, Context.MODE_PRIVATE)

        MaterialAlertDialogBuilder( requireContext(), R.style.ThemeOverlay_MaterialComponents_MaterialCalendar_Fullscreen)
            .setMessage( resources.getString( R.string.message_logout ))
            .setTitle( resources.getString( R.string.title ))
            .setNegativeButton( resources.getString(R.string.no), null)
            .setPositiveButton( resources.getString(R.string.si), { dialog, which ->
                // Respond to positive button press
                auth.signOut()
                deleteSession()
                val intent =  Intent( requireContext(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }).show()

        return binding
    }

    private fun deleteSession() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
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