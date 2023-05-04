package com.example.feature.content.details.di

import androidx.lifecycle.ViewModel
import com.example.api.di.ApiDeps
import com.example.api.di.ApiDepsProvider
import com.example.feature.content.details.data.ArticleDetailsRepositoryImpl
import com.example.feature.content.details.domain.ArticleDetailsRepository
import com.example.feature.content.details.presentation.ArticleDetailsFragment
import dagger.Binds
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Scope
annotation class ArticleDetailScope

@ArticleDetailScope
@Component(modules = [ArticleDetailRepositoryBindModule::class], dependencies = [ApiDeps::class])
interface ArticleDetailComponent {

    fun inject(fragment: ArticleDetailsFragment)

    @Component.Builder
    interface Builder {
        fun deps(deps: ApiDeps): Builder

        fun build(): ArticleDetailComponent
    }

}

internal class ArticleDetailComponentViewModel : ViewModel() {

    val newsListComponent =
        DaggerArticleDetailComponent.builder().deps(ApiDepsProvider.deps).build()

}

@Module
internal interface ArticleDetailRepositoryBindModule {

    @Binds
    fun bindArticleDetailRepository(impl: ArticleDetailsRepositoryImpl): ArticleDetailsRepository

}
