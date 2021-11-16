package com.espressoit.airquality.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MeasurementStation(
    val id: Int,
    val stationName: String,
    val cityName: String,
    val provinceName: String,
    val districtName: String
) : Parcelable
