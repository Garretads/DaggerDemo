package com.example.feature.content.details.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import com.example.feature.content.details.R
import com.example.feature.content.details.di.ArticleDetailComponentViewModel
import com.example.feature.content.details.presentation.ArticleDetailsArgs.getArticleId
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class ArticleDetailsFragment : Fragment() {

    private val articleId: String by lazy { getArticleId() }

    @Inject
    internal lateinit var assistedFactory: ArticleDetailsViewModel.Factory.Factory

    private val articleDetailsViewModel: ArticleDetailsViewModel by viewModels {
        assistedFactory.create(articleId)
    }

    private lateinit var holder: ArticleDetailsViewHolder

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<ArticleDetailComponentViewModel>().newsListComponent.inject(this)
        super.onAttach(context)
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

        articleDetailsViewModel.article.onEach {
            holder.contentText.text = it?.content
        }.launchIn(lifecycleScope)
    }

}
