package com.example.headhunter.presentation.vacanciesliset

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.headhunter.R
import com.example.headhunter.appComponent
import com.example.headhunter.databinding.FragmentVacanciesBinding
import com.example.headhunter.presentation.vacancyinfo.VacancyInfoFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "xdd"
class VacanciesFragment : Fragment() {
    private lateinit var binding:FragmentVacanciesBinding

    @Inject lateinit var factory: VacanciesViewModel.Factory
    private val vModel: VacanciesViewModel by viewModels{ factory }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentVacanciesBinding.inflate(inflater,container,false)
        val adapter = VacanciesPagingAdapter(){ openInfoFragment(it) }

        lifecycleScope.launch {
            vModel.items.collectLatest { adapter.submitData(it) }
        }

        val adapterWithLoadStateFooter = adapter.withLoadStateFooter(VacanciesLoadStateAdapter(){
            adapter.retry()
        })
        binding.rcViewVacancies.adapter = adapterWithLoadStateFooter
        return binding.root
    }

    private fun openInfoFragment(it: String) {
        parentFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(
                R.id.container,
                VacancyInfoFragment::class.java,
                bundleOf(VacancyInfoFragment.ID_KEY to it)
            )
            .commit()
    }
}