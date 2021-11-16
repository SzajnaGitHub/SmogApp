package com.espressoit.airquality.di

import android.app.Application
import androidx.room.Room
import com.espressoit.airquality.AirQualityRepository
import com.espressoit.airquality.internal.AirQualityRepositoryImpl
import com.espressoit.airquality.internal.local.AirQualityDatabase
import com.espressoit.airquality.internal.remote.AirQualityApi
import com.espressoit.airquality.util.Constants
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class AirQualityModule {

    @Provides
    @Singleton
    fun provideAirQualityApi(builder: Retrofit.Builder): AirQualityApi = builder
        .baseUrl(Constants.AIR_QUALITY_API_BASE_URL)
        .build()
        .create(AirQualityApi::class.java)

    @Provides
    @Singleton
    fun provideAirQualityDatabase(context: Application): AirQualityDatabase = Room.databaseBuilder(
        context, AirQualityDatabase::class.java, Constants.AIR_QUALITY_DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideMeasurementStationDao(db: AirQualityDatabase) = db.measurementDao
}

@Module
@InstallIn(SingletonComponent::class)
internal interface InternalAirQualityModule {

    @Binds
    @Singleton
    fun provideAirQualityRepository(internalRepository: AirQualityRepositoryImpl): AirQualityRepository
}
