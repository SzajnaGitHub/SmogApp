package com.espressoit.airquality.internal.mapper

import com.espressoit.airquality.internal.local.entity.MeasurementStationEntity
import com.espressoit.airquality.internal.remote.dto.MeasurementStationDto
import com.espressoit.airquality.model.MeasurementStation

internal fun MeasurementStationDto.toMeasurementStationEntity(): MeasurementStationEntity = MeasurementStationEntity(
    id = id,
    stationName = stationName,
    cityName = city.name,
    provinceName = city.commune.provinceName,
    districtName = city.commune.districtName
)

internal fun MeasurementStationEntity.toMeasurementStation(): MeasurementStation = MeasurementStation(
    id = id,
    stationName = stationName,
    cityName = cityName,
    provinceName = provinceName,
    districtName = districtName
)
