package com.example.daggertraining.di

import android.app.Application
import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import com.example.api.ApiService
import com.example.api.buildApiService
import com.example.api.di.ApiDeps
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope
import kotlin.properties.Delegates

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent: ApiDeps {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        @BindsInstance
        fun apiKey(key: String): Builder

        fun build(): AppComponent

    }

}


@Scope
annotation class AppScope

@Module(includes = [ApiModule::class])
interface AppModule

@Module
class ApiModule {

    @[Provides AppScope]
    fun providesApiService(apiKey: String): ApiService = buildApiService(apiKey)

}
