package com.example.feature.content.details.presentation

import android.view.View
import android.webkit.WebView
import android.widget.TextView
import com.example.feature.content.details.R

data class ArticleDetailsViewHolder(
    val view: View,
    val contentText: TextView,
) {

    companion object {

        fun create(view: View) =
            ArticleDetailsViewHolder(
                view = view,
                contentText = view.findViewById(R.id.articleContent),
            )

    }

}
