package com.example.headhunter.presentation.vacanciesliset

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.data.model.PagerDataParamsParcel
import com.example.headhunter.R
import com.example.headhunter.appComponent
import com.example.headhunter.databinding.FragmentVacanciesBinding
import com.example.headhunter.databinding.SearchPanelBinding
import com.example.headhunter.presentation.utils.SearchSettings
import com.example.headhunter.presentation.vacancyinfo.VacancyInfoFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "xdd"
class VacanciesFragment : Fragment() {
    private lateinit var binding:FragmentVacanciesBinding
    private var params = PagerDataParamsParcel()
    @Inject lateinit var factory: VacanciesViewModel.Factory
    private val vModel: VacanciesViewModel by viewModels{ factory }
    private lateinit var adapter: VacanciesPagingAdapter
    private lateinit var dialod: BottomSheetDialog
    private lateinit var dialogBinding:SearchPanelBinding
    private lateinit var searchSettings: SearchSettings
    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        @Suppress("DEPRECATION")
        params = savedInstanceState?.getParcelable(pKey) as? PagerDataParamsParcel ?: params
        super.onCreate(savedInstanceState)
    }
    private val pKey = "SearchParams"
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(pKey, params)
        super.onSaveInstanceState(outState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentVacanciesBinding.inflate(inflater,container,false)
        inflateDialog()
        searchSettings = SearchSettings(requireContext())
        adapter = VacanciesPagingAdapter{ openInfoFragment(it) }
        search()

        val adapterWithLoadStateFooter = adapter.withLoadStateFooter(VacanciesLoadStateAdapter{
            adapter.retry()
        })
        binding.rcViewVacancies.adapter = adapterWithLoadStateFooter

        inflateSearchPanel()

        return binding.root
    }

    private fun inflateDialog() {
        dialogBinding = SearchPanelBinding.inflate(layoutInflater)
        dialod = BottomSheetDialog(requireContext())
        dialod.setContentView(dialogBinding.root)
        binding.imageButtonFullSettings.setOnClickListener {
            dialod.show()
        }
    }

    private fun inflateSearchPanel() {
        binding.textSearch.addTextChangedListener {
            params.text = it.toString()
        }
        binding.textSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                search()
                return@setOnEditorActionListener true
            }
            false
        }
        dialogBinding.onlyWithSalary.setOnCheckedChangeListener { _, isChecked ->
            params.onlyWithSalary = isChecked
        }
        dialogBinding.salary.addTextChangedListener {
            val str = it.toString()
            if (str.isNotEmpty())
                params.salary = str.toInt()
        }
        dialogBinding.searchButton.setOnClickListener {
            search()
        }

        initCheckboxListeners()
        dialogBinding.experienceSpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            searchSettings.experience.keys.toList()
        )
    }

    private fun initCheckboxListeners() {
        /* employment checkboxes*/
        dialogBinding.employmentFullCheckbox.setOnCheckedChangeListener { _, isChecked ->
            val key = searchSettings.employment[getString(R.string.full_employment)]!!
            if (isChecked) params.employmentIds[0] = key
            else params.employmentIds[0] = null
        }
        dialogBinding.employmentPartCheckbox.setOnCheckedChangeListener { _, isChecked ->
            val key = searchSettings.employment[getString(R.string.part_employment)]!!
            if (isChecked) params.employmentIds[1] = key
            else params.employmentIds[1] = null
        }
        dialogBinding.employmentProjectCheckbox.setOnCheckedChangeListener { _, isChecked ->
            val key = searchSettings.employment[getString(R.string.project_employment)]!!
            if (isChecked) params.employmentIds[2] = key
            else params.employmentIds[2] = null
        }
        dialogBinding.employmentVolunteerCheckbox.setOnCheckedChangeListener { _, isChecked ->
            val key = searchSettings.employment[getString(R.string.volunteer_employment)]!!
            if (isChecked) params.employmentIds[3] = key
            else params.employmentIds[3] = null
        }
        dialogBinding.employmentProbationCheckbox.setOnCheckedChangeListener { _, isChecked ->
            val key = searchSettings.employment[getString(R.string.probation_employment)]!!
            if (isChecked) params.employmentIds[4] = key
            else params.employmentIds[4] = null
        }
        /* schedule checkboxes*/
        dialogBinding.scheduleFullDay.setOnCheckedChangeListener { _, isChecked ->
            val key = searchSettings.schedule[getString(R.string.full_day_schedule)]!!
            if (isChecked) params.scheduleIds[0] = key
            else params.scheduleIds[0] = null
        }
        dialogBinding.scheduleShift.setOnCheckedChangeListener { _, isChecked ->
            val key = searchSettings.schedule[getString(R.string.shift_schedule)]!!
            if (isChecked) params.scheduleIds[1] = key
            else params.scheduleIds[1] = null
        }
        dialogBinding.scheduleFlexible.setOnCheckedChangeListener { _, isChecked ->
            val key = searchSettings.schedule[getString(R.string.flexible_schedule)]!!
            if (isChecked) params.scheduleIds[2] = key
            else params.scheduleIds[2] = null
        }
        dialogBinding.scheduleRemote.setOnCheckedChangeListener { _, isChecked ->
            val key = searchSettings.schedule[getString(R.string.remote_schedule)]!!
            if (isChecked) params.scheduleIds[3] = key
            else params.scheduleIds[3] = null
        }
        dialogBinding.scheduleFlyInFlyOut.setOnCheckedChangeListener { _, isChecked ->
            val key = searchSettings.schedule[getString(R.string.fly_in_fly_out_schedule)]!!
            if (isChecked) params.scheduleIds[4] = key
            else params.scheduleIds[4] = null
        }
    }

    private fun search(){
        runCatching {
            params.experienceId = searchSettings.experience[
                    dialogBinding.experienceSpinner.selectedItem.toString()]
        }
        dialod.hide()
        binding.textSearch.clearFocus()
        lifecycleScope.launch {
            vModel.createFlow(params).collectLatest { adapter.submitData(it) }
        }
    }

    private fun openInfoFragment(it: String) {
        parentFragmentManager.beginTransaction()
            .addToBackStack(null)
            .hide(this)
            .add(
                R.id.container,
                VacancyInfoFragment::class.java,
                bundleOf(VacancyInfoFragment.ID_KEY to it)
            )
            .commit()
    }
}