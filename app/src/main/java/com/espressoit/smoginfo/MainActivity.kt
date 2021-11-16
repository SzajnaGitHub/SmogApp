package com.espressoit.smoginfo

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.espressoit.navigation.Destinations
import com.espressoit.navigation.NavigationManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var navigator: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)
        observeNavigationEvents()
    }

    private fun observeNavigationEvents() {
        lifecycleScope.launch {
            navigator.observeNavigationEvents().collect { event ->
                when (event.destination) {
                    Destinations.STATION_DETAILS -> R.id.action_stationListFragment_to_stationDetailsFragment
                    else -> null
                }?.let { destination ->
                    findNavController(R.id.nav_host_fragment).navigate(destination, event.arguments)
                }
            }
        }
    }
}
