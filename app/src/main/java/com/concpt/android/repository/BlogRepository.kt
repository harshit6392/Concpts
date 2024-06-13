package com.concpt.android.repository

import com.concpt.android.network.RetrofitInstance

class BlogRepository {
    private val api = RetrofitInstance.api

    suspend fun getBlogPosts() = api.getBlogPosts()
}
