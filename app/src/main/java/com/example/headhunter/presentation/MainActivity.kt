package com.example.headhunter.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.headhunter.R
import com.example.headhunter.presentation.vacanciesliset.VacanciesFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState==null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, VacanciesFragment())
                .commit()
    }
}