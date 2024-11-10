package com.example.retrofitapi

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapi.databinding.ActivityMainBinding
import com.example.retrofitapi.ui.theme.PostResponseItem
import com.example.retrofitapi.ui.theme.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        getPosts()
        getPostsById(1)
    }

    private fun getPostsById(postId: Int) {
        RetrofitInstance.api.getPostById(postId).enqueue(object : Callback<PostResponseItem> {
            override fun onResponse(call: Call<PostResponseItem>, response: Response<PostResponseItem>) {
                if (response.isSuccessful && response.body() != null) {
                    val post = response.body()!!
                    Log.d("MainActivity", "Post retrieved: $post")
                    postAdapter = PostAdapter(listOf(post))
                    recyclerView.adapter = postAdapter
                } else {
                    Log.e("MainActivity", "Failed to retrieve post by ID: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PostResponseItem>, t: Throwable) {
                Log.e("MainActivity", "API call failed: ${t.message}")
            }
        })
    }

    private fun getPosts() {
        RetrofitInstance.api.getPosts().enqueue(object : Callback<List<PostResponseItem>> {
            override fun onResponse(
                call: Call<List<PostResponseItem>>,
                response: Response<List<PostResponseItem>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val posts = response.body()!!
                    Log.d("MainActivity", "Posts retrieved: $posts")  // Log full response
                    postAdapter = PostAdapter(posts)
                    recyclerView.adapter = postAdapter  // Set adapter here
                } else {
                    Log.e("MainActivity", "Failed to retrieve posts: ${response.message()} - ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<PostResponseItem>>, t: Throwable) {
                Log.e("MainActivity", "API call failed: ${t.message}")
            }
        })
    }
}
