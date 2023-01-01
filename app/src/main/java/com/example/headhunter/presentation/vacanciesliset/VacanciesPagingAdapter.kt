package com.example.headhunter.presentation.vacanciesliset

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.headhunter.databinding.ItemVacancyBinding
import com.example.headhunter.model.data.pagerdata.Items
import com.example.headhunter.model.data.pagerdata.Salary

class VacanciesPagingAdapter(private val openVacancy:(id:String) -> Unit)
    : PagingDataAdapter<Items, VacanciesPagingAdapter.VacanciesViewHolder>(ItemsCallBack()) {
    class VacanciesViewHolder(val binding:ItemVacancyBinding):RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: VacanciesViewHolder, position: Int) {
        val item = getItem(position)!!
        holder.binding.root.setOnClickListener {
            if (item.id !=null) openVacancy(item.id!!)
            else Log.wtf(TAG, "onBindViewHolder: item id is null")
        }
        holder.binding.employer.text = item.employer?.name
        holder.binding.salary.text = initSalary(item.salary)
        holder.binding.vacancyName.text = item.name
        holder.binding.location.text = item.area?.name
        holder.binding.requirement.text = item.snippet?.requirement
    }

    private fun initSalary(salary: Salary?): String {
        var str = ""
        if (salary?.from!= null) str += "От ${salary.from}"
        if (salary?.from!= null && salary.to!=null) str += " "
        if (salary?.to!=null) str += "До ${salary.to}"
        return str.ifEmpty { "Зарплата не указана" }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacanciesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemVacancyBinding.inflate(inflater,parent,false)
        return VacanciesViewHolder(binding)
    }
}

class ItemsCallBack : DiffUtil.ItemCallback<Items>(){
    override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean =
        oldItem == newItem
}