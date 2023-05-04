package com.example.feature.content.list.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.api.ApiService
import com.example.feature.content.list.ListRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import javax.inject.Provider

class ContentListViewModel(
    private val repository: ListRepository,
): ViewModel() {

    private companion object {
        private const val TAG = "ArticlesViewModel"
    }

    @SuppressWarnings("TooGenericExceptionCaught")
    val articles = flow {
        try {
            emit(repository.allArticles())
        } catch (e: Exception) {
            Log.d(TAG, "Error", e)
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    class Factory @Inject constructor(
        private val newsRepository: Provider<ListRepository>
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == ContentListViewModel::class.java)
            return ContentListViewModel(newsRepository.get()) as T
        }

    }

}
