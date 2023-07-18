package com.bamboogeeks.julyacademy2.firebasestorageapp.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bamboogeeks.julyacademy2.databinding.ItemFirebaseImagesBinding
import com.bumptech.glide.Glide

class FirebaseImagesAdapter(var urls: List<String>) :
    RecyclerView.Adapter<FirebaseImagesAdapter.FirebaseImagesViewHolder>() {

    inner class FirebaseImagesViewHolder(var binding: ItemFirebaseImagesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirebaseImagesViewHolder {
        return FirebaseImagesViewHolder(
            ItemFirebaseImagesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return urls.size
    }

    override fun onBindViewHolder(holder: FirebaseImagesViewHolder, position: Int) {
        val urls = urls[position]
        Glide.with(holder.itemView).load(urls).into(holder.binding.ivFirebaseImagesItem)
    }
}