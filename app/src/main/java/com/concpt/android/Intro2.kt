package com.concpt.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Intro2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro2)

        val intro2Button = findViewById<android.widget.Button>(R.id.intro2Button)
        intro2Button.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}