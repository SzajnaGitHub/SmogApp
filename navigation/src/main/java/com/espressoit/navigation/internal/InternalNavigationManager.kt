package com.espressoit.navigation.internal

import android.os.Bundle
import com.espressoit.navigation.NavigationEvent
import com.espressoit.navigation.NavigationManager
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

internal class InternalNavigationManager @Inject constructor() : NavigationManager {

    private val navigationEvent = MutableSharedFlow<NavigationEvent>()

    override suspend fun navigateTo(destination: String, arguments: Bundle) {
        navigationEvent.emit(NavigationEvent(destination, arguments))
    }

    override fun observeNavigationEvents(): SharedFlow<NavigationEvent> = navigationEvent.asSharedFlow()
}
