package com.example.headhunter.presentation.vacancyinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.headhunter.databinding.ItemSkillBinding

class KeySkillsAdapter(private val keySkills:List<String?>): RecyclerView.Adapter<KeySkillsAdapter.KeySkillsViewHolder>() {
    class KeySkillsViewHolder(val binding: ItemSkillBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeySkillsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSkillBinding.inflate(inflater,parent,false)
        return KeySkillsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KeySkillsViewHolder, position: Int) {
        holder.binding.name.text = keySkills[position]
    }

    override fun getItemCount() = keySkills.size
}