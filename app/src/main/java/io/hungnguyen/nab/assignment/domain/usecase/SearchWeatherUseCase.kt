package io.hungnguyen.nab.assignment.domain.usecase

import io.hungnguyen.nab.assignment.domain.model.WeatherEntity
import io.hungnguyen.nab.assignment.domain.repository.WeatherRepository
import javax.inject.Inject

class SearchWeatherUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    suspend fun searchWeathers(query: String): Result<List<WeatherEntity>> {
        return weatherRepository.searchWeathers(query)
    }
}