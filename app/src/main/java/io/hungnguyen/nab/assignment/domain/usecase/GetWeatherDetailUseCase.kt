package io.hungnguyen.nab.assignment.domain.usecase

import io.hungnguyen.nab.assignment.domain.model.WeatherEntity
import io.hungnguyen.nab.assignment.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherDetailUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    suspend fun getWeatherDetail(id: Long): Result<WeatherEntity> {
        return weatherRepository.getWeatherDetail(id)
    }
}

