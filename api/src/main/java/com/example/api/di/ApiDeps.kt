package com.example.api.di

import androidx.annotation.RestrictTo
import com.example.api.ApiService
import kotlin.properties.Delegates

interface ApiDeps {

    val apiService: ApiService

}

interface ApiDepsProvider {

    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: ApiDeps

    companion object : ApiDepsProvider by ApiDepsStore
}

object ApiDepsStore : ApiDepsProvider {

    override var deps: ApiDeps by Delegates.notNull()
}
