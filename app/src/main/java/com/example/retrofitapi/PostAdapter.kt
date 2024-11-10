package com.example.retrofitapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapi.databinding.ItemPostBinding
import com.example.retrofitapi.ui.theme.PostResponseItem

class PostAdapter(private val posts: List<PostResponseItem>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])  // Pass single post item to bind
    }

    override fun getItemCount(): Int = posts.size

    class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: PostResponseItem) {
            binding.tvTitle.text = post.title
            binding.tvBody.text = post.body
        }
    }
}
