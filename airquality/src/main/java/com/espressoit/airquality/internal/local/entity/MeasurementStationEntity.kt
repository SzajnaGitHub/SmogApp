package com.espressoit.airquality.internal.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MeasurementStationEntity(
    @PrimaryKey val id: Int,
    val stationName: String,
    val cityName: String,
    val provinceName: String,
    val districtName: String
)
