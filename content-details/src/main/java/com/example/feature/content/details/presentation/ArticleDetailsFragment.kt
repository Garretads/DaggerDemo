package com.example.feature.content.details.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.feature.content.details.R
import com.example.feature.content.details.presentation.ArticleDetailsArgs.getArticleUrl


class ArticleDetailsFragment : Fragment() {

    private val articleUrl: String? by lazy { getArticleUrl() }

    private lateinit var holder: ArticleDetailsViewHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ArticleDetailsViewHolder.create(
        inflater.inflate(R.layout.fragment_article_details, container, false)
    ).also {
        holder = it
    }.view

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        holder.webView.webViewClient = WebViewClient()

        articleUrl?.let {
            holder.webView.loadUrl(it)
        }
    }

}
