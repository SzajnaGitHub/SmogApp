package com.espressoit.airquality.internal.mapper

import com.espressoit.airquality.internal.remote.dto.SensorDto
import com.espressoit.airquality.model.Sensor

internal fun SensorDto.toSensor(): Sensor = Sensor(
    id = id,
    paramCode = param.paramCode,
    paramName = param.paramName
)
