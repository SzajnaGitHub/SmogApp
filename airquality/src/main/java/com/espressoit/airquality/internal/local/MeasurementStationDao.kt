package com.espressoit.airquality.internal.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.espressoit.airquality.internal.local.entity.MeasurementStationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MeasurementStationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeasurementStations(stations: List<MeasurementStationEntity>)

    @Query("DELETE FROM measurementstationentity")
    suspend fun clearData()

    @Query("SELECT * FROM measurementstationentity WHERE cityName LIKE :query || '%' OR provinceName LIKE :query || '%'")
    fun getStationsByCityNameOrProvinceName(query: String): Flow<List<MeasurementStationEntity>>

    @Query("SELECT * FROM measurementstationentity WHERE id IS :id")
    suspend fun getStationById(id: Int): MeasurementStationEntity
}
