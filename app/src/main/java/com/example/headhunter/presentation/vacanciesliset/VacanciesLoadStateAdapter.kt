package com.example.headhunter.presentation.vacanciesliset

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.headhunter.databinding.LoadStateBinding

class VacanciesLoadStateAdapter(private val retry:() -> Unit) : LoadStateAdapter<VacanciesLoadStateAdapter.LoadStateViewHolder>() {
    class LoadStateViewHolder(val binding:LoadStateBinding):RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.binding.buttonRetry.setOnClickListener{retry()}
        if (loadState is LoadState.Error || loadState is LoadState.NotLoading){
            holder.binding.buttonRetry.visibility = View.VISIBLE
            holder.binding.progress.visibility = View.GONE
        }else{
            holder.binding.buttonRetry.visibility = View.GONE
            holder.binding.progress.visibility = View.VISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LoadStateBinding.inflate(inflater,parent,false)
        return LoadStateViewHolder(binding)
    }
}