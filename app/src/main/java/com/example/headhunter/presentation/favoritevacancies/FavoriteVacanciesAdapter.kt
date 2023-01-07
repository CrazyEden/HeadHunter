package com.example.headhunter.presentation.favoritevacancies

import android.annotation.SuppressLint
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.RoomDataEntity
import com.example.headhunter.databinding.ItemVacancyBinding

class FavoriteVacanciesAdapter(
    val openVacancy :(vacancy:  RoomDataEntity) -> Unit
):RecyclerView.Adapter<FavoriteVacanciesAdapter.VHolder>() {
    class VHolder(val binding: ItemVacancyBinding) : RecyclerView.ViewHolder(binding.root)
    private var vacancies = mutableListOf<RoomDataEntity>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(vacancies:MutableList<RoomDataEntity>?){
        if (vacancies == null) return
        this.vacancies = vacancies
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        val item = vacancies[position]
        holder.binding.root.setOnClickListener {
            openVacancy(item)
        }
        holder.binding.employer.text = item.employer
        holder.binding.salary.text = item.salary
        holder.binding.vacancyName.text = item.vacancyName
        holder.binding.location.text = item.area
        holder.binding.requirement.text = Html.fromHtml(
            item.descriptions,
            Html.FROM_HTML_MODE_COMPACT
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemVacancyBinding.inflate(inflater,parent,false)
        return VHolder(binding)
    }

    override fun getItemCount(): Int = vacancies.size
}