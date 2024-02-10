package com.concpt.android

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Intro1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro1)

        //navigate to intro2 activity
        val b : Button = findViewById(R.id.intro1Button)
        b.setOnClickListener {
            startActivity(Intent(this, Intro2::class.java))
            finish()
        }
        val b2 : Button = findViewById(R.id.skip)
        b2.setOnClickListener {
            startActivity(Intent(this, Intro2::class.java))
        }
    }
}