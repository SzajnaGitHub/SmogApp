package com.espressoit.airquality.internal.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.espressoit.airquality.internal.local.entity.MeasurementStationEntity

@Database(
    entities = [MeasurementStationEntity::class],
    version = 1
)
abstract class AirQualityDatabase : RoomDatabase() {

    abstract val measurementDao: MeasurementStationDao
}
