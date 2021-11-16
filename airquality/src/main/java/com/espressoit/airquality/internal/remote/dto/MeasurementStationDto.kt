package com.espressoit.airquality.internal.remote.dto

data class MeasurementStationDto(
    val addressStreet: String?,
    val city: CityDto,
    val gegrLat: String,
    val gegrLon: String,
    val id: Int,
    val stationName: String
)
