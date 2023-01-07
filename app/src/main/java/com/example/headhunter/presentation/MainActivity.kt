package com.example.headhunter.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.headhunter.R
import com.example.headhunter.databinding.ActivityMainBinding
import com.example.headhunter.presentation.favoritevacancies.FavoriteVacanciesFragment
import com.example.headhunter.presentation.vacanciesliset.VacanciesFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState==null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, VacanciesFragment())
                .commit()
        binding.bottomNavMenu.setOnItemSelectedListener {
            when(it.itemId){
                R.id.mainfh->{
                    it.isChecked = true
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, VacanciesFragment())
                        .commit()
                    true
                }
                R.id.favoritefh->{
                    it.isChecked = true
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, FavoriteVacanciesFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}