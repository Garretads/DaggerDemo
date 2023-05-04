package com.example.feature.content.details.presentation

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

object ArticleDetailsArgs {

    private const val ARTICLE_URL = "article_url"

    fun getBundle(articleUrl: String) = bundleOf(ARTICLE_URL to articleUrl)

    internal fun Fragment.getArticleUrl(): String? = arguments?.getString(ARTICLE_URL, null)

}
