package com.espressoit.measurementstationlist

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.espressoit.airquality.AirQualityRepository
import com.espressoit.common.exception.ExceptionHandler
import com.espressoit.common.extensions.launch
import com.espressoit.navigation.Destinations
import com.espressoit.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MeasurementStationListViewModel @Inject constructor(
    private val airQualityRepository: AirQualityRepository,
    private val navigator: NavigationManager,
    private val exceptionHandler: ExceptionHandler
) : ViewModel() {

    private val _viewState = MutableStateFlow(StationListViewState.INIT)
    val viewState: StateFlow<StationListViewState> = _viewState

    private val searchQuery = MutableStateFlow("")

    private val stationsFlow = searchQuery.flatMapLatest {
        airQualityRepository.getAllStations(it)
    }

    init {
        observeStations()
    }

    private fun observeStations() = launch(exceptionHandler) {
        stationsFlow.distinctUntilChanged().collect {
            _viewState.value = viewState.value.copy(stations = it)
        }
    }


    fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }

    fun navigateToDetailsView(id: Int) = launch(exceptionHandler) {
        navigator.navigateTo(
            destination = Destinations.STATION_DETAILS,
            arguments = Bundle().apply {
                putInt("STATION_ID", id)
            }
        )
    }
}
