package io.hungnguyen.nab.assignment.domain.repository

import io.hungnguyen.nab.assignment.domain.model.WeatherEntity

interface WeatherRepository {
    suspend fun searchWeathers(query: String): Result<List<WeatherEntity>>

    suspend fun getWeatherDetail(id: Long): Result<WeatherEntity>
}