package com.example.healthcareappointment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

    }
}