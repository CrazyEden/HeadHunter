package com.example.headhunter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.headhunter.databinding.ItemVacancyBinding
import com.example.headhunter.model.data.Items

class VacanciesPagingAdapter()
    : PagingDataAdapter<Items, VacanciesPagingAdapter.VacanciesViewHolder>(ItemsCallBack()) {
    class VacanciesViewHolder(val binding:ItemVacancyBinding):RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: VacanciesViewHolder, position: Int) {
        val item = getItem(position)!!
        holder.binding.company.text = item.contacts.toString()
        holder.binding.salary.text = item.salary.toString()
        holder.binding.vacancyName.text = item.name
        holder.binding.location.text = item.area?.name
        holder.binding.requirement.text = item.snippet?.requirement
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