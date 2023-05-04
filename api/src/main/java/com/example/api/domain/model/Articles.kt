package com.example.api.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Articles(
    @SerializedName("articles")
    val articles: List<Article>
)
