package com.example.feature.content.details.data

import com.example.api.ApiService
import com.example.api.domain.model.Article
import com.example.feature.content.details.domain.ArticleDetailsRepository
import javax.inject.Inject

class ArticleDetailsRepositoryImpl @Inject constructor(
    private val service: ApiService
) : ArticleDetailsRepository {

    override suspend fun getArticle(id: String): Article =
        service.allArticles().articles.first()

}
