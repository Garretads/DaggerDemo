package com.example.feature.content.list.presentation

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
import androidx.navigation.fragment.findNavController
import com.example.api.domain.model.Article
import com.example.feature.content.details.presentation.ArticleDetailsArgs
import com.example.feature.content.list.R
import com.example.feature.content.list.di.NewsListComponentViewModel
import com.example.feature.content.list.presentation.adapter.NewsListAdapter
import dagger.Lazy
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class ContentListFragment : Fragment(R.layout.fragment_content_list) {

    @Inject
    internal lateinit var articlesViewModelFactory: Lazy<ContentListViewModel.Factory>

    private val articlesViewModel: ContentListViewModel by viewModels {
        articlesViewModelFactory.get()
    }

    lateinit var holder: ContentListViewHolder

    private val adapter: NewsListAdapter by lazy { NewsListAdapter() }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<NewsListComponentViewModel>().newsListComponent.inject(this)
        super.onAttach(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ContentListViewHolder.create(
        inflater.inflate(R.layout.fragment_content_list, container, false)
    ).also {
        holder = it
    }.view

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        holder.recyclerView.adapter = adapter
        adapter.onItemClick = ::onItemSelected

        articlesViewModel.articles.onEach { articles ->
            adapter.submitList(articles)
        }.launchIn(lifecycleScope)

    }

    private fun onItemSelected(article: Article) {
        val url = article.url ?: return

        val args = ArticleDetailsArgs.getBundle(url)

        findNavController().navigate(R.id.articleDetailsFragment, args)
    }

}
