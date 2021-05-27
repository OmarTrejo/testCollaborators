package com.its_omar.testcollaborators

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import com.its_omar.testcollaborators.View.IndexActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()


    }

    companion object {
        private const val TAG = "emailPassword"
    }


    override fun onStart() {
        super.onStart()

        // Valida que el usuario se haya logueado
        val currentUser = auth.currentUser
        if (currentUser != null) reload()
    }

    private fun reload() {
        TODO("Not yet implemented")
        Log.d(TAG, "reload: " )
    }

    private fun signIn ( email: String, password: String )
    {
        // Start signin with email
        auth.signInWithEmailAndPassword( email, password)
            .addOnCompleteListener( this ) { task ->
                if ( task.isSuccessful )
                {
                    // Logueo completo
                    Log.d(TAG, "signIn: signInWithEmail:success")
                    logIn()
                }
                else
                {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                    Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun logIn() {
        val intent = Intent(this, IndexActivity::class.java)
        startActivity(intent)
    }
}