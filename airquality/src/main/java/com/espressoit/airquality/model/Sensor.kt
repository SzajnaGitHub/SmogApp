package com.espressoit.airquality.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sensor(
    val id: Int,
    val paramCode: String,
    val paramName: String
) : Parcelable
