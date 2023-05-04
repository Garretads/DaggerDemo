package com.example.feature.content.list.data

import com.example.api.ApiService
import com.example.api.domain.model.Article
import com.example.feature.content.list.domain.ListRepository
import javax.inject.Inject

class ListRepositoryImpl @Inject constructor(
    val service: ApiService
) : ListRepository {


    override suspend fun allArticles(): List<Article> = service.allArticles().articles

}
