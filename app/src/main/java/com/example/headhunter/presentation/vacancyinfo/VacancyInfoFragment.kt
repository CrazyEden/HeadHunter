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
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.domain.model.RoomData
import com.example.domain.model.vacancyinfo.VacancyInfo
import com.example.domain.usecase.localdata.SaveVacancyUseCase
import com.example.headhunter.appComponent
import com.example.headhunter.databinding.FragmentVacancyInfoBinding
import com.example.headhunter.presentation.utils.toCompactString
import com.example.headhunter.presentation.vacanciesliset.TAG
import kotlinx.coroutines.launch
import javax.inject.Inject


class VacancyInfoFragment : Fragment() {
    private lateinit var binding:FragmentVacancyInfoBinding

    @Inject lateinit var factory : VacancyInfoViewModel.Factory.Factoryy

    @Inject lateinit var saveVacancyUseCase: SaveVacancyUseCase
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
        binding.salary.text = vacancyInfo.salary?.toCompactString
        val exp = "Требуемый опыт: ${vacancyInfo.experience?.name}"
        binding.experienceName.text = exp
        val sch = "${vacancyInfo.schedule?.name}, ${vacancyInfo.employment?.name}"
        binding.scheduleName.text = sch

        binding.employer.text = vacancyInfo.employer?.name
        binding.employerImage.load(
        vacancyInfo.employer?.logoUrls?.original
            ?: vacancyInfo.employer?.logoUrls?._240
            ?: "https://cdn3.iconfinder.com/data/icons/human-resources-management/512/job_vacancy_work_oppotunity-1024.png"
        ){
            crossfade(true)
            crossfade(1000)
            transformations(RoundedCornersTransformation(30f))
        }
        val des = Html.fromHtml(vacancyInfo.description,FROM_HTML_MODE_COMPACT).toString()
        binding.employerImage.setOnClickListener {
            lifecycleScope.launch {
                saveVacancyUseCase.execute(RoomData(
                    vacancyId = vacancyInfo.id!!,
                    vacancyName = vacancyInfo.name!!,
                    salary = vacancyInfo.salary.toCompactString,
                    experience = vacancyInfo.experience?.name!!,
                    schedule = vacancyInfo.schedule?.name!!,
                    employer = vacancyInfo.employer?.name!!,
                    area = vacancyInfo.area?.name!!,
                    descriptions = des,
                    keySkills = vacancyInfo.keySkills[0].name!!,
                ))
            }
        }
        binding.area.text = vacancyInfo.area?.name
        binding.descriptions.text = des

        Log.d(TAG, "inflateView: ${vacancyInfo.keySkills.count()}")
        if (vacancyInfo.keySkills.isNotEmpty())
            binding.keySkills.adapter = KeySkillsAdapter(vacancyInfo.keySkills.map { it.name })
        else binding.keySkillsTextView.visibility = View.GONE
    }

    companion object{
        const val ID_KEY = "VACANCY_ID"
    }
}