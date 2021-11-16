package com.espressoit.navigation.di

import com.espressoit.navigation.NavigationManager
import com.espressoit.navigation.internal.InternalNavigationManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface NavigationModule {

    @Binds
    @Singleton
    fun bindNavigationManager(internalNavManager: InternalNavigationManager): NavigationManager
}
