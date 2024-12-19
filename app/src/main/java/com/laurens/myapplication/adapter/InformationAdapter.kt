package com.laurens.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.laurens.myapplication.data.varClass
import com.laurens.myapplication.databinding.ItemCardBinding

class InformationAdapter (private val listSkinDisease: ArrayList<varClass>) : RecyclerView.Adapter<InformationAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root)


    open class OnItemClickCallback {
        open fun onItemClicked(data: varClass) {}
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listSkinDisease.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val (name, description, photo) = listSkinDisease[position]
        holder.binding.imgItemPhoto.setImageResource(photo)
        holder.binding.tvItemName.text = name
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listSkinDisease[holder.adapterPosition])
        }
    }
}