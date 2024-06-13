package com.concpt.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.concpt.android.adapter.BlogAdapter
import com.concpt.android.databinding.ActivityHomeBinding
import com.concpt.android.repository.BlogRepository
import com.concpt.android.viewModel.BlogViewModel
import com.concpt.android.viewModel.BlogViewModelFactory

class HomeActivity : AppCompatActivity() {

    private lateinit var blogViewModel: BlogViewModel
    private lateinit var blogAdapter: BlogAdapter
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        blogAdapter = BlogAdapter(emptyList())
        binding.recyclerView.adapter = blogAdapter

        // Initialize SearchView
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Perform search/filter operation here
                return true
            }
        })

        // Initialize ViewModel and Repository
        val repository = BlogRepository()
        blogViewModel = ViewModelProvider(this, BlogViewModelFactory(repository))[BlogViewModel::class.java]

        // Observe blog data
        blogViewModel.blogPosts.observe(this) { blogPosts ->
            blogAdapter.updateItems(blogPosts)
        }

        // Fetch the blog posts
        blogViewModel.fetchBlogPosts()
    }
}
