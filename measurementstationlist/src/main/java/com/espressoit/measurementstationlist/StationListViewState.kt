package com.espressoit.measurementstationlist

import com.espressoit.airquality.model.MeasurementStation

data class StationListViewState(
    val isLoading: Boolean,
    val stations: List<MeasurementStation>
) {

    companion object {
        val INIT: StationListViewState = StationListViewState(
            isLoading = true,
            stations = emptyList()
        )
    }
}
