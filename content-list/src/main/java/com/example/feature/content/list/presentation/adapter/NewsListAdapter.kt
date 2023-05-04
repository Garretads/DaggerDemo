package com.example.feature.content.list.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.api.domain.Article
import com.example.feature.content.list.R

internal class NewsListAdapter : ListAdapter<Article, ArticleViewHolder>(ArticleItemCallback()) {

    var onItemClick: ((Article) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater.inflate(R.layout.item_article, parent, false)

        return ArticleViewHolder.create(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
        holder.view.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }
}

internal class ArticleViewHolder(
    val view: View,
    val content: TextView,
    val title: TextView,
) : RecyclerView.ViewHolder(view) {

    fun bind(article: Article) {
        title.text = article.title
        content.text = article.content
    }

    companion object {

        fun create(view: View) =
            ArticleViewHolder(
                view = view,
                content = view.findViewById(R.id.contentId),
                title = view.findViewById(R.id.titleId),
            )

    }

}

private class ArticleItemCallback : DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }
}
