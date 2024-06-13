package com.concpt.android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.concpt.android.Models.BlogItemModel
import com.concpt.android.R
import com.bumptech.glide.Glide
import com.concpt.android.databinding.BlogItemBinding

class BlogAdapter(private var items: List<BlogItemModel>) :
    RecyclerView.Adapter<BlogAdapter.BlogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BlogItemBinding.inflate(inflater, parent, false)
        return BlogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class BlogViewHolder(private val binding: BlogItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(blogItemModel: BlogItemModel) {
            binding.heading.text = blogItemModel.heading
            val imageUrl = blogItemModel.imageurl
            if (imageUrl != null && imageUrl.isNotEmpty()) {
                Glide.with(binding.root.context)
                    .load(imageUrl)
                    .into(binding.profileb)
            } else {
                binding.profileb.setImageResource(R.drawable.person_outlined)
            }
            binding.userName.text = blogItemModel.userName
            binding.date.text = blogItemModel.date
            binding.post.text = blogItemModel.post
            binding.likeCount.text = blogItemModel.likeCount.toString()
        }
    }

    fun updateItems(newItems: List<BlogItemModel>) {
        items = newItems
        notifyDataSetChanged()
    }
}
