package com.espressoit.airquality.internal.remote.dto

data class SensorDto(
    val id: Int,
    val param: ParamDto,
    val stationId: Int
)
