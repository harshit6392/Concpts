package com.concpt.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.concpt.android.Models.User
import com.concpt.android.databinding.ActivitySignUpBinding
import com.google.android.gms.common.SignInButton
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class SignUpActivity : AppCompatActivity() {
    val binding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        user = User()
        binding.signupButton.setOnClickListener {
            // Handle the click event, for example, start a new activity
            if (binding.nameEditText.text.toString().isEmpty() or
                binding.loginEmailEditText.text.toString().isEmpty() or
                binding.passwordEditText.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.loginEmailEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        user.name = binding.nameEditText.text.toString()
                        user.password = binding.passwordEditText.text.toString()
                        user.email = binding.loginEmailEditText.text.toString()
                        Firebase.firestore.collection("users")
                            .document(Firebase.auth.currentUser!!.uid)
                            .set(user)
                            .addOnSuccessListener {
                                Toast.makeText(
                                    this,
                                    "User created successfully",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                val intent = Intent(this, HomeActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            .addOnFailureListener {
                                Toast.makeText(this, "Error: ${it.message}", Toast.LENGTH_SHORT)
                                    .show()
                            }

                    } else {
                        Toast.makeText(this, "Error: ${it.exception?.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }


        val continueWithGoogleButton: SignInButton = findViewById(R.id.continueWithGoogleButton)
        continueWithGoogleButton.setStyle(SignInButton.SIZE_WIDE, SignInButton.COLOR_LIGHT)
    }

    fun onLoginClick(view: View) {
        // Handle the click event, for example, start a new activity
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }
}