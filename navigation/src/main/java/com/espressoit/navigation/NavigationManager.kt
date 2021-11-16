package com.espressoit.navigation

import android.os.Bundle
import kotlinx.coroutines.flow.SharedFlow

interface NavigationManager {

    suspend fun navigateTo(destination: String, arguments: Bundle = Bundle.EMPTY)

    fun observeNavigationEvents(): SharedFlow<NavigationEvent>
}
