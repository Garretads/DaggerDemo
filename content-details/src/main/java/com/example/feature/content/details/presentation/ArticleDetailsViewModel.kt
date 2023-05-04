package com.example.feature.content.details.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.feature.content.details.domain.ArticleDetailsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import javax.inject.Provider

class ArticleDetailsViewModel(
    private val articleId: String,
    private val repository: ArticleDetailsRepository,
) : ViewModel() {

    private companion object {
        private const val TAG = "ArticleDetailViewModel"
    }

    @SuppressWarnings("TooGenericExceptionCaught")
    val article = flow {
        try {
            emit(repository.getArticle(articleId))
        } catch (e: Exception) {
            Log.d(TAG, "Error", e)
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, null)


    class Factory @AssistedInject constructor(
        @Assisted("articleId") private val articleId: String,
        private val articleDetailRepository: ArticleDetailsRepository
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == ArticleDetailsViewModel::class.java)
            return ArticleDetailsViewModel(articleId, articleDetailRepository) as T
        }

        @AssistedFactory
        interface Factory {
            fun create(@Assisted("articleId") articleId: String): ArticleDetailsViewModel.Factory
        }

    }

}
