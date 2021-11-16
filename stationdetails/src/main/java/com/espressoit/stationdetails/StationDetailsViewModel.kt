package com.espressoit.stationdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.espressoit.airquality.AirQualityRepository
import com.espressoit.common.exception.ExceptionHandler
import com.espressoit.common.extensions.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class StationDetailsViewModel @Inject constructor(
    private val airQualityRepository: AirQualityRepository,
    private val exceptionHandler: ExceptionHandler,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _viewState = MutableStateFlow(StationDetailsViewState.INIT)
    val viewState: StateFlow<StationDetailsViewState> = _viewState

    init {
        savedStateHandle.get<Int>("STATION_ID")?.let {
            getStationById(it)
            getAllSensorsForStation(it)
        }
    }

    private fun getStationById(id: Int) = launch(exceptionHandler) {
        airQualityRepository.getStationById(id)
            .onSuccess { measurementStation ->
                _viewState.value = viewState.value.copy(station = measurementStation)
            }
            .onFailure {
                _viewState.value = viewState.value.copy(error = true)
            }
    }

    private fun getAllSensorsForStation(id: Int) = launch(exceptionHandler) {
        airQualityRepository.getStationSensors(id)
            .onSuccess { sensors ->
                _viewState.value = viewState.value.copy(sensors = sensors)
            }
            .onFailure {
                _viewState.value = viewState.value.copy(error = true)
            }
    }
}
