package com.concpt.android.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.concpt.android.Models.BlogItemModel
import com.concpt.android.repository.BlogRepository
import kotlinx.coroutines.launch

class BlogViewModel(private val repository: BlogRepository) : ViewModel() {

    private val _blogPosts = MutableLiveData<List<BlogItemModel>>()
    val blogPosts: LiveData<List<BlogItemModel>> = _blogPosts

    fun fetchBlogPosts() {
        viewModelScope.launch {
            try {
                val posts = repository.getBlogPosts()
                _blogPosts.postValue(posts)
            } catch (e: Exception) {
                e.printStackTrace() // Handle error appropriately
            }
        }
    }
}
