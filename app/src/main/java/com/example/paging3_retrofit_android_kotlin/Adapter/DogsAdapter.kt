package com.example.paging3_retrofit_android_kotlin.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.paging3_retrofit_android_kotlin.data.Dogs
import com.example.paging3_retrofit_android_kotlin.databinding.EachRowBinding
import javax.inject.Inject

class DogsAdapter
@Inject constructor()
    : PagingDataAdapter<Dogs, DogsAdapter.DogsViewHolder>(Diff())
{

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        val dogs = getItem(position)
        if(dogs!= null){
            holder.binds(dogs)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder  =
        DogsViewHolder(EachRowBinding.inflate(LayoutInflater
            .from(parent.context),parent,false))

    class DogsViewHolder(private val binding: EachRowBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun binds(dogs: Dogs){
            binding.apply {
//                image.load(dogs.url)
                Glide.with(binding.image)
                    .load(dogs.url)
                    .transition(DrawableTransitionOptions.withCrossFade(200))
                    .into(binding.image)
            }
        }
    }

    class Diff : DiffUtil.ItemCallback<Dogs>(){
        override fun areItemsTheSame(oldItem: Dogs, newItem: Dogs): Boolean  =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: Dogs, newItem: Dogs): Boolean =
            oldItem == newItem
    }
}