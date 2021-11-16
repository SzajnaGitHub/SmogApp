package com.espressoit.airquality.internal.remote.dto

data class CityDto(
    val commune: CommuneDto,
    val id: Int,
    val name: String
)
