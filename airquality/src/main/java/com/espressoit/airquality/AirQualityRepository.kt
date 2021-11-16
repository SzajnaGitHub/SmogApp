package com.espressoit.airquality

import com.espressoit.airquality.model.MeasurementStation
import com.espressoit.airquality.model.Sensor
import kotlinx.coroutines.flow.Flow

interface AirQualityRepository {

    suspend fun getAllStations(searchQuery: String): Flow<List<MeasurementStation>>

    suspend fun getStationById(id: Int): Result<MeasurementStation>

    suspend fun getStationSensors(stationId: Int): Result<List<Sensor>>
}
