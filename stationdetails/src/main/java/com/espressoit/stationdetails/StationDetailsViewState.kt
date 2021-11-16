package com.espressoit.stationdetails

import com.espressoit.airquality.model.MeasurementStation
import com.espressoit.airquality.model.Sensor

data class StationDetailsViewState(
    val isLoading: Boolean,
    val station: MeasurementStation? = null,
    val sensors: List<Sensor> = emptyList(),
    val error: Boolean
) {

    companion object {
        val INIT: StationDetailsViewState = StationDetailsViewState(
            isLoading = true,
            station = null,
            error = false
        )
    }
}
