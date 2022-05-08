package io.hungnguyen.nab.assignment.data.repository

import io.hungnguyen.nab.assignment.data.mapper.WeatherMapper
import io.hungnguyen.nab.assignment.data.model.RequestException
import io.hungnguyen.nab.assignment.data.service.WeatherService
import io.hungnguyen.nab.assignment.domain.model.Weather
import io.hungnguyen.nab.assignment.domain.repository.WeatherRepository
import retrofit2.HttpException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
    private val mapper: WeatherMapper
) : WeatherRepository {

    private val cachedList: MutableList<Weather> = mutableListOf()

    override suspend fun searchWeathers(query: String): Result<List<Weather>> {
        return try {
            val responseBody = weatherService.searchWeathers(query).body()
            val weathers = responseBody?.weathers?.map { mapper.mapFromEntity(it) } ?: emptyList()
            cachedList.addAll(weathers)
            Result.success(weathers)
        } catch (e: HttpException){
            Result.failure(
                RequestException(
                    code = e.code(),
                    message = e.message()
                )
            )
        }
    }

    override suspend fun getWeatherDetail(id: Long): Result<Weather> {
        return cachedList.find { it.id == id }?.let { weather ->
            Result.success(weather)
        } ?: run {
            Result.failure(Exception("An error occurred when get weather detail"))
        }
    }
}