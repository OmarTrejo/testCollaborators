package com.its_omar.testcollaborators

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.its_omar.testcollaborators.View.IndexActivity

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class LoginActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences
    private val preferences = "colaboradores_preferences"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val BtnIniciarSesion = findViewById<MaterialButton>(R.id.BtnIniciarSesion)
        val ETxtPassword = findViewById<TextInputEditText>(R.id.ETxtPassword)
        val ETxtEmail = findViewById<TextInputEditText>(R.id.ETxtEmail)

        auth = FirebaseAuth.getInstance()

        // Inicializa shared preferences
        sharedPreferences = this.getSharedPreferences( preferences, Context.MODE_PRIVATE )

        if ( validateLogin() )
        {
            val intent = Intent(this, IndexActivity::class.java)
            startActivity(intent)
        }

        BtnIniciarSesion.setOnClickListener {
            // Validar que haya datos
            if (ETxtEmail.length() > 0 && ETxtPassword.length() > 0) {
                if (validateEmail(ETxtEmail.text.toString())) {
                    signIn( ETxtEmail.text.toString(), ETxtPassword.text.toString() )
                }
                else
                {
                    Toast.makeText(this, "Correo electrónico invalido", Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(this, "Datos incompletos, intentalo de nuevo", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun validateLogin(): Boolean {
        return sharedPreferences.getBoolean( "sesion_iniciada", false )
    }

    private fun validateEmail(text: String?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(text).matches()
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

                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putBoolean( "sesion_iniciada" , true )
                    editor.apply()
                    editor.commit()

                    logIn()
                }
                else
                {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Error, correo o contraseña incorrectos.",
                    Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun logIn() {
        val intent = Intent(this, IndexActivity::class.java)
        startActivity(intent)
        finish()
    }
}