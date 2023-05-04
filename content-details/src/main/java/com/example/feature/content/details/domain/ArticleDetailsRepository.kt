package com.example.feature.content.details.domain

import com.example.api.domain.model.Article

interface ArticleDetailsRepository {

    suspend fun getArticle(id: String): Article

}
