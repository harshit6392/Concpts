package com.concpt.android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.concpt.android.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Handle the click event of the "Login" button.
        binding.loginbutton.setOnClickListener {
            if (binding.loginEmailEditText.text.toString().isEmpty() or
                binding.passwordEditText.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.loginEmailEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Error: ${it.exception?.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
        // Set the dimensions of the Google sign-in button.
        val signInWithGoogleButton: SignInButton = findViewById(R.id.signInWithGoogleButton)
        signInWithGoogleButton.setStyle(SignInButton.SIZE_WIDE, SignInButton.COLOR_LIGHT)
    }

    // Handle the click event of the "Forget Password" button.
    fun onForgetPasswordClick(view: View) {
        // Handle the click event, for example, start a new activity
        val intent = Intent(this, ForgetPasswordActivity::class.java)
        startActivity(intent)
    }

    fun onBackClick(view: View) {
        // Handle the click event, for example, start a new activity
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}