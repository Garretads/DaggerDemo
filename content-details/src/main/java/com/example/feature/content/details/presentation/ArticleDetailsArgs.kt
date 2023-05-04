package com.example.feature.content.details.presentation

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

object ArticleDetailsArgs {

    private const val ARTICLE_ID = "article_id"

    fun getBundle(articleId: String) = bundleOf(ARTICLE_ID to articleId)

    internal fun Fragment.getArticleId(): String = arguments?.getString(ARTICLE_ID, "") ?: ""

}
