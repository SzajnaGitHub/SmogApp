package com.espressoit.airquality.internal

import com.espressoit.airquality.AirQualityRepository
import com.espressoit.airquality.internal.local.MeasurementStationDao
import com.espressoit.airquality.internal.mapper.toMeasurementStation
import com.espressoit.airquality.internal.mapper.toMeasurementStationEntity
import com.espressoit.airquality.internal.mapper.toSensor
import com.espressoit.airquality.internal.remote.AirQualityApi
import com.espressoit.airquality.model.MeasurementStation
import com.espressoit.airquality.model.Sensor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class AirQualityRepositoryImpl @Inject constructor(
    private val api: AirQualityApi,
    private val measurementStationDao: MeasurementStationDao
) : AirQualityRepository {

    override suspend fun getAllStations(searchQuery: String): Flow<List<MeasurementStation>> = runCatching {
        api.getAllStations()
            .map { it.toMeasurementStationEntity() }
    }
        .onSuccess { measurementStations ->
            measurementStationDao.clearData()
            measurementStationDao.insertMeasurementStations(measurementStations)
        }
        .run {
            measurementStationDao.getStationsByCityNameOrProvinceName(searchQuery)
                .distinctUntilChanged()
                .map { items ->
                    items.map { it.toMeasurementStation() }
                }
        }

    override suspend fun getStationById(id: Int): Result<MeasurementStation> = runCatching {
        measurementStationDao.getStationById(id).toMeasurementStation()
    }

    override suspend fun getStationSensors(stationId: Int): Result<List<Sensor>> = runCatching {
        api.getStationSensors(stationId).map { it.toSensor() }
    }
}
