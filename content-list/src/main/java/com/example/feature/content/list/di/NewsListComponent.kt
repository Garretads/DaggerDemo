package com.example.feature.content.list.di

import androidx.lifecycle.ViewModel
import com.example.api.ApiService
import com.example.api.di.ApiDeps
import com.example.api.di.ApiDepsProvider
import com.example.feature.content.list.ListRepository
import com.example.feature.content.list.ListRepositoryImpl
import com.example.feature.content.list.presentation.ContentListFragment
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
annotation class ListScope

@ListScope
@Component(modules = [NewsListModule::class, ListRepositoryBindModule::class], dependencies = [ApiDeps::class])
interface NewsListComponent {
    fun inject(fragment: ContentListFragment)

    @Component.Builder
    interface Builder {
        fun deps(deps: ApiDeps): Builder

        fun build(): NewsListComponent
    }

}

internal class NewsListComponentViewModel : ViewModel() {

    val newsListComponent =
        DaggerNewsListComponent.builder().deps(ApiDepsProvider.deps).build()

}

@Module
class NewsListModule {

    @Provides
    fun providesListRepository(
        service: ApiService
    ): ListRepositoryImpl = ListRepositoryImpl(
        service = service
    )

}

@Module
interface ListRepositoryBindModule {

    @Binds
    fun bindListRepository(impl: ListRepositoryImpl): ListRepository

}
