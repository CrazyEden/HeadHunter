package com.example.headhunter.presentation.vacancyinfo

import android.content.Context
import android.os.Bundle
import android.text.Html
import android.text.Html.FROM_HTML_MODE_COMPACT
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.headhunter.appComponent
import com.example.headhunter.databinding.FragmentVacancyInfoBinding
import com.example.headhunter.model.data.vacancyinfo.VacancyInfo
import com.example.headhunter.presentation.vacanciesliset.TAG
import javax.inject.Inject


class VacancyInfoFragment : Fragment() {
    private lateinit var binding:FragmentVacancyInfoBinding

    @Inject
    lateinit var factory : VacancyInfoViewModel.Factory.Factoryy

    private val vModel:VacancyInfoViewModel by viewModels{
        factory.create(arguments?.getString(ID_KEY)!!)
    }


    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVacancyInfoBinding.inflate(inflater,container,false)

        vModel.vacancyInfoLiveData.observe(viewLifecycleOwner){ inflateView(it) }



        return binding.root
    }

    private fun inflateView(vacancyInfo: VacancyInfo) {
        binding.progressBar.visibility = View.GONE

        binding.vacancyName.text = vacancyInfo.name
        binding.salary.text = vacancyInfo.salary.toString()
        binding.experienceName.text = vacancyInfo.experience?.name
        binding.scheduleName.text = vacancyInfo.schedule?.name

        binding.employer.text = vacancyInfo.employer?.name
        binding.area.text = vacancyInfo.area?.name
        binding.descriptions.text = Html.fromHtml(vacancyInfo.description,FROM_HTML_MODE_COMPACT)
        Log.d(TAG, "inflateView: ${vacancyInfo.keySkills.count()}")
//        binding.keySkills.setHasFixedSize(false)
        binding.keySkills.adapter = KeySkillsAdapter(vacancyInfo.keySkills.map { it.name })
    }

    companion object{
        const val ID_KEY = "VACANCY_ID"
    }
}