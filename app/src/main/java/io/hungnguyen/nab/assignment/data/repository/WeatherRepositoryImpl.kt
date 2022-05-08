package io.hungnguyen.nab.assignment.data.repository

import io.hungnguyen.nab.assignment.data.mapper.WeatherMapper
import io.hungnguyen.nab.assignment.data.service.WeatherService
import io.hungnguyen.nab.assignment.domain.model.WeatherEntity
import io.hungnguyen.nab.assignment.domain.repository.WeatherRepository
import retrofit2.HttpException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
    private val mapper: WeatherMapper
) : WeatherRepository {

    private val cachedList: MutableList<WeatherEntity> = mutableListOf()

    override suspend fun searchWeathers(query: String): Result<List<WeatherEntity>> {
        return try {
            val responseBody = weatherService.searchWeathers(query).body()
            val weathers = responseBody?.weathers?.map { mapper.mapToEntity(it) } ?: emptyList()
            cachedList.addAll(weathers)
            Result.success(weathers)
        } catch (e: HttpException){
            Result.failure(e)
        }
    }

    override suspend fun getWeatherDetail(id: Long): Result<WeatherEntity> {
        return cachedList.find { it.id == id }?.let { weather ->
            Result.success(weather)
        } ?: run {
            Result.failure(Exception("An error occurred when get weather detail"))
        }
    }
}