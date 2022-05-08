package io.hungnguyen.nab.assignment.domain.repository

import io.hungnguyen.nab.assignment.domain.model.Weather

interface WeatherRepository {
    suspend fun searchWeathers(query: String): Result<List<Weather>>

    suspend fun getWeatherDetail(id: Long): Result<Weather>
}