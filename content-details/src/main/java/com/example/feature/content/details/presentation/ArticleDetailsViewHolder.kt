package com.example.feature.content.details.presentation

import android.view.View
import android.webkit.WebView

data class ArticleDetailsViewHolder(
    val view: View,
    val webView: WebView,
) {

    companion object {

        fun create(view: View) =
            ArticleDetailsViewHolder(
                view = view,
                webView = view as WebView,
            )

    }

}
