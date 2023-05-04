package com.example.feature.content.list.domain

import com.example.api.domain.model.Article

interface ListRepository {

    suspend fun allArticles(): List<Article>

}
