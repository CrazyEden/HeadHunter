package com.example.headhunter.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.headhunter.R
import com.example.headhunter.presentation.vacanciesliset.VacanciesFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, VacanciesFragment())
            .commit()
    }
}