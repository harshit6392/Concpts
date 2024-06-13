package com.concpt.android.network

import retrofit2.http.GET
import com.concpt.android.Models.BlogItemModel


interface BlogService {
    @GET("wp-json/wp/v2/posts?per_page=10&page=1")
    suspend fun getBlogPosts(): List<BlogItemModel>
}
