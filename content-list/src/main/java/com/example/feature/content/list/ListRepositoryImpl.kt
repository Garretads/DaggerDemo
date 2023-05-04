package com.example.feature.content.list

import com.example.api.ApiService
import com.example.api.domain.Article

class ListRepositoryImpl(
    val service: ApiService
) : ListRepository {


    override suspend fun allArticles(): List<Article> = service.allArticles().articles

}
