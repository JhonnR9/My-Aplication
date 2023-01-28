package me.jhonn.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonSignIn: Button
    private lateinit var textViewSignUp: TextView
    private lateinit var checkBoxRememberMe: CheckBox

    private lateinit var mFirebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        referenceViews()

        mFirebaseAuth = FirebaseAuth.getInstance()

        buttonSignIn.setOnClickListener {
            val loginEmail: String = editTextEmail.text.toString()
            val loginPassword: String = editTextPassword.text.toString()

            if (loginEmail.isNotEmpty() && loginPassword.isNotEmpty()) {
                mFirebaseAuth.signInWithEmailAndPassword(loginEmail, loginPassword)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                           val intent :Intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            val error: String? = it.exception?.message
                            Toast.makeText(applicationContext, error, Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }

    }

    private fun referenceViews() {
        editTextEmail = findViewById(R.id.text_input_edit_text_email)
        editTextPassword = findViewById(R.id.text_input_edit_text_password)
        buttonSignIn = findViewById(R.id.button_sing_in)
        textViewSignUp = findViewById(R.id.text_view_sign_up)
        checkBoxRememberMe = findViewById(R.id.check_box_remenber_me)
    }
}