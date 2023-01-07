package com.example.headhunter.presentation.vacancyinfo

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.text.Html
import android.text.Html.FROM_HTML_MODE_COMPACT
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
        val vacancy = arguments?.getParcelable(VACANCY_KEY) as? RoomDataEntity
        if (vacancy == null){
            vModel.vacancyInfoLiveData.observe(viewLifecycleOwner){ inflateView(it) }
            vModel.isVacancyFavoriteLiveData.observe(viewLifecycleOwner){
                isVacancyFavorite = it
                binding.iconFavorite.setImageResource(
                    if (it) R.drawable.ic_heart_fill
                    else R.drawable.ic_heart
                )
            }
        } else {
            inflateView(vacancy)
        }
        return binding.root
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
        val des = Html.fromHtml(vacancyInfo.descriptions,FROM_HTML_MODE_COMPACT).toString()
        binding.iconFavorite.setImageResource(R.drawable.ic_heart_fill)
        binding.iconFavorite.setOnClickListener {
            onHeartClick(vacancyInfo)
        }
        binding.area.text = vacancyInfo.area
        binding.descriptions.text = des
        if (vacancyInfo.keySkills?.isNotEmpty() == true)
            binding.keySkills.adapter = KeySkillsAdapter(vacancyInfo.keySkills)
        else binding.keySkillsTextView.visibility = View.GONE
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
        val des = Html.fromHtml(vacancyInfo.description,FROM_HTML_MODE_COMPACT).toString()

        binding.iconFavorite.setOnClickListener {
            onHeartClick(vacancyInfo,binding.employerImage.drawToBitmap())
        }
        binding.area.text = vacancyInfo.area?.name
        binding.descriptions.text = des
        if (vacancyInfo.keySkills.isNotEmpty())
            binding.keySkills.adapter = KeySkillsAdapter(vacancyInfo.keySkills.map { it.name })
        else binding.keySkillsTextView.visibility = View.GONE
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
        Log.d(TAG, "onHeartClick: $isVacancyFavorite")
    }
    private fun onHeartClick(data: RoomDataEntity) {

        if (isVacancyFavorite){
            isVacancyFavorite = false
            vModel.removeVacancyFromFavoriteList(data)
            binding.iconFavorite.setImageResource(R.drawable.ic_heart)
            Log.d(TAG, "onHeartClick: removed")
        } else {
            isVacancyFavorite = true
            vModel.makeVacancyFavorite(data)
            binding.iconFavorite.setImageResource(R.drawable.ic_heart_fill)
            Log.d(TAG, "onHeartClick: added")
        }
    }

    companion object{
        const val ID_KEY = "VACANCY_ID"
        const val VACANCY_KEY = "vacancy_item"
    }
}