package com.espressoit.airquality.internal.remote

import com.espressoit.airquality.internal.remote.dto.MeasurementStationDto
import com.espressoit.airquality.internal.remote.dto.SensorDto
import retrofit2.http.GET
import retrofit2.http.Path

internal interface AirQualityApi {

    @GET("station/findAll")
    suspend fun getAllStations(): List<MeasurementStationDto>

    @GET("station/sensors/{stationId}")
    suspend fun getStationSensors(@Path("stationId") stationId: Int): List<SensorDto>
}
