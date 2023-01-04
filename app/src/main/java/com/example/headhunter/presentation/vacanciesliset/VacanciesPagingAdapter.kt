package com.example.headhunter.presentation.vacanciesliset

import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.headhunter.databinding.ItemVacancyBinding
import com.example.headhunter.presentation.utils.toCompactString

class VacanciesPagingAdapter(private val openVacancy:(id:String) -> Unit)
    : PagingDataAdapter<com.example.domain.model.pagerdata.Items, VacanciesPagingAdapter.VacanciesViewHolder>(ItemsCallBack()) {
    class VacanciesViewHolder(val binding:ItemVacancyBinding):RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: VacanciesViewHolder, position: Int) {
        val item = getItem(position)!!
        holder.binding.root.setOnClickListener {
            if (item.id !=null) openVacancy(item.id!!)
            else Log.wtf(TAG, "onBindViewHolder: item id is null")
        }
        holder.binding.employer.text = item.employer?.name
        holder.binding.salary.text = item.salary?.toCompactString
        holder.binding.vacancyName.text = item.name
        holder.binding.location.text = item.area?.name
        holder.binding.requirement.text = Html.fromHtml(
            item.snippet?.requirement,
            Html.FROM_HTML_MODE_COMPACT
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacanciesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemVacancyBinding.inflate(inflater,parent,false)
        return VacanciesViewHolder(binding)
    }
}

class ItemsCallBack : DiffUtil.ItemCallback<com.example.domain.model.pagerdata.Items>(){
    override fun areItemsTheSame(oldItem: com.example.domain.model.pagerdata.Items, newItem: com.example.domain.model.pagerdata.Items): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: com.example.domain.model.pagerdata.Items, newItem: com.example.domain.model.pagerdata.Items): Boolean =
        oldItem == newItem
}