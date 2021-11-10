package com.example.playground

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.playground.databinding.ListItemBinding

class RecyclerViewAdapter(val list: ArrayList<ListItem>) : RecyclerView.Adapter<RecyclerViewAdapter
.ViewHolder>
    () {

    inner class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            tvText.text = list[position].text
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}