package com.espressoit.navigation

import android.os.Bundle

data class NavigationEvent(
    val destination: String,
    val arguments: Bundle = Bundle.EMPTY
)
