package com.example.headhunter.presentation.vacancyinfo

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.domain.model.RoomDataEntity
import com.example.domain.model.vacancyinfo.VacancyInfo
import com.example.domain.units.toCompactString
import com.example.headhunter.R
import com.example.headhunter.appComponent
import com.example.headhunter.databinding.FragmentVacancyInfoBinding
import com.example.headhunter.presentation.vacanciesliset.TAG
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


class VacancyInfoFragment : Fragment() {
    private lateinit var binding:FragmentVacancyInfoBinding

    @Inject lateinit var factory : VacancyInfoViewModel.Factory.Factoryy
    private var isVacancyFavorite = false

    private val vModel:VacancyInfoViewModel by viewModels{
        factory.create(arguments?.getString(ID_KEY))
    }


    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("isVacancyFavorite",isVacancyFavorite)
        super.onSaveInstanceState(outState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVacancyInfoBinding.inflate(inflater,container,false)
        isVacancyFavorite = savedInstanceState?.getBoolean("isVacancyFavorite",false) ?: false
        @Suppress("DEPRECATION")
        val vacancy = arguments?.getParcelable<RoomDataEntity?>(VACANCY_KEY)
        if (vacancy != null) {
            inflateView(vacancy)
            return binding.root
        }

        tryLoad()
        binding.buttonRetryInfo.setOnClickListener {
            tryLoad()
        }
        vModel.vacancyInfoLiveData.observe(viewLifecycleOwner){
            binding.progressBar.visibility = View.GONE
            if (it != null) {
                inflateView(it)
                return@observe
            }
            binding.buttonRetryInfo.visibility = View.VISIBLE
        }
        vModel.isVacancyFavoriteLiveData.observe(viewLifecycleOwner){
            isVacancyFavorite = it
            binding.iconFavorite.setImageResource(
                if (it) R.drawable.ic_heart_fill
                else R.drawable.ic_heart
            )
        }
        return binding.root
    }

    private fun tryLoad(){
        binding.progressBar.visibility = View.VISIBLE
        vModel.loadVacancy()
    }

    private fun inflateView(vacancyInfo: RoomDataEntity){
        binding.progressBar.visibility = View.GONE
        isVacancyFavorite = true
        binding.iconFavorite.isClickable = true
        binding.vacancyName.text = vacancyInfo.vacancyName
        binding.salary.text = vacancyInfo.salary
        val exp = "Требуемый опыт: ${vacancyInfo.experience}"
        binding.experienceName.text = exp
        val sch = "${vacancyInfo.schedule}, ${vacancyInfo.schedule}"
        binding.scheduleName.text = sch

        binding.employer.text = vacancyInfo.employer
        binding.employerImage.load(vacancyInfo.image ?: R.drawable.employer_place_holder){
            crossfade(true)
            crossfade(1000)
            transformations(RoundedCornersTransformation(30f))
        }
        val des = Html.fromHtml(vacancyInfo.htmlDescription, FROM_HTML_MODE_LEGACY)
        binding.descriptions.text = des
        binding.iconFavorite.setImageResource(R.drawable.ic_heart_fill)
        binding.iconFavorite.setOnClickListener {
            onHeartClick(vacancyInfo)
        }
        binding.area.text = vacancyInfo.area
        Log.w(TAG, "${vacancyInfo.keySkills}", )
        Log.w(TAG, "${vacancyInfo.keySkills.none()}", )
        Log.w(TAG, "${vacancyInfo.keySkills.isEmpty()}", )
        if (vacancyInfo.keySkills.isEmpty())
            binding.keySkillsTextView.visibility = View.GONE
        else binding.keySkills.adapter = KeySkillsAdapter(vacancyInfo.keySkills)
    }
    private fun inflateView(vacancyInfo: VacancyInfo) {
        binding.progressBar.visibility = View.GONE
        binding.buttonRetryInfo.visibility = View.GONE
        binding.vacancyName.text = vacancyInfo.name
        binding.salary.text = vacancyInfo.salary?.toCompactString
        val exp = "Требуемый опыт: ${vacancyInfo.experience?.name}"
        binding.experienceName.text = exp
        val sch = "${vacancyInfo.schedule?.name}, ${vacancyInfo.employment?.name}"
        binding.scheduleName.text = sch

        binding.employer.text = vacancyInfo.employer?.name
        binding.employerImage.load(
        vacancyInfo.employer?.logoUrls?._240
            ?: vacancyInfo.employer?.logoUrls?.original
            ?: R.drawable.employer_place_holder
        ){
            crossfade(true)
            crossfade(650)
            transformations(RoundedCornersTransformation(30f))
            listener { _, _ ->
                lifecycleScope.launch {
                    delay(1000)
                    binding.iconFavorite.isClickable = true
                }
            }
        }
        val des = Html.fromHtml(vacancyInfo.description, FROM_HTML_MODE_LEGACY)
        binding.descriptions.text = des
        binding.iconFavorite.setOnClickListener {
            onHeartClick(vacancyInfo,binding.employerImage.drawToBitmap())
        }
        binding.area.text = vacancyInfo.area?.name
        if (vacancyInfo.keySkills.isEmpty())
            binding.keySkillsTextView.visibility = View.GONE
        else binding.keySkills.adapter = KeySkillsAdapter(vacancyInfo.keySkills.map { it.name })
    }

    private fun onHeartClick(vacancyInfo: VacancyInfo, bitmap:Bitmap) {
        val data = vacancyInfo.toRoomDataEntity(bitmap)
        if (isVacancyFavorite){
            isVacancyFavorite = false
            vModel.removeVacancyFromFavoriteList(data)
            binding.iconFavorite.setImageResource(R.drawable.ic_heart)
        } else {
            vModel.makeVacancyFavorite(data)
            isVacancyFavorite = true
            binding.iconFavorite.setImageResource(R.drawable.ic_heart_fill)
        }
    }
    private fun onHeartClick(data: RoomDataEntity) {

        if (isVacancyFavorite){
            isVacancyFavorite = false
            vModel.removeVacancyFromFavoriteList(data)
            binding.iconFavorite.setImageResource(R.drawable.ic_heart)
        } else {
            isVacancyFavorite = true
            vModel.makeVacancyFavorite(data)
            binding.iconFavorite.setImageResource(R.drawable.ic_heart_fill)
        }
    }

    companion object{
        const val ID_KEY = "VACANCY_ID"
        const val VACANCY_KEY = "vacancy_item"
    }
}