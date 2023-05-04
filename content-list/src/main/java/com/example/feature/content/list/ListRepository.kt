package com.example.feature.content.list

import com.example.api.domain.Article

interface ListRepository {

    suspend fun allArticles(): List<Article>

}
