package com.example.headhunter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.headhunter.databinding.FragmentVacanciesBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

val TAG = "xdd"
class VacanciesFragment : Fragment() {
    private lateinit var binding:FragmentVacanciesBinding

    @Inject lateinit var factory:VacanciesViewModel.Factory
    private val vModel:VacanciesViewModel by viewModels{ factory }
    override fun onAttach(context: Context) {
        Log.w(TAG, "onAttach: ")
        context.appComponent.inject(this)
        super.onAttach(context)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentVacanciesBinding.inflate(inflater,container,false)
        val adapter = VacanciesPagingAdapter()
        Log.w(TAG, "onCreateView: ")

        lifecycleScope.launch {
            vModel.items.collectLatest { adapter.submitData(it) }
        }
        Log.w(TAG, "onCreateView: ")


        binding.rcViewVacancies.adapter = adapter
        Log.w(TAG, "onCreateView: ")

        return binding.root
    }
}