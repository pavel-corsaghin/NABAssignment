package io.hungnguyen.nab.assignment.domain.usecase

import io.hungnguyen.nab.assignment.domain.model.Weather
import io.hungnguyen.nab.assignment.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherDetailUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    suspend fun getWeatherDetail(id: Long): Result<Weather> {
        return weatherRepository.getWeatherDetail(id)
    }
}

