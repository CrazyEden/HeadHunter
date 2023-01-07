package com.example.headhunter.presentation.favoritevacancies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.headhunter.R
import com.example.headhunter.appComponent
import com.example.headhunter.databinding.FragmentFavoriteVacanciesBinding
import com.example.headhunter.presentation.vacancyinfo.VacancyInfoFragment
import javax.inject.Inject


class FavoriteVacanciesFragment : Fragment() {
    lateinit var binding:FragmentFavoriteVacanciesBinding
    @Inject
    lateinit var factory: FavoriteVacanciesViewModel.Factory
    private val vModel: FavoriteVacanciesViewModel by viewModels { factory }
    lateinit var adapter: FavoriteVacanciesAdapter

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteVacanciesBinding.inflate(inflater,container,false)
        vModel.updateUi()
        vModel.vacanciesLiveData.observe(viewLifecycleOwner){
            adapter.setData(it)
        }

        adapter = FavoriteVacanciesAdapter{
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(
                    R.id.container,
                    VacancyInfoFragment::class.java,
                    bundleOf(VacancyInfoFragment.VACANCY_KEY to it)
                )
                .commit()
        }

        binding.rcViewFavoriteVacancies.adapter = adapter
        return binding.root
    }

}