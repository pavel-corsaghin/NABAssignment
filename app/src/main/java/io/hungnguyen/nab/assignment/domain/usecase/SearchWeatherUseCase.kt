package io.hungnguyen.nab.assignment.domain.usecase

import io.hungnguyen.nab.assignment.domain.model.Weather
import io.hungnguyen.nab.assignment.domain.repository.WeatherRepository
import javax.inject.Inject

class SearchWeatherUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    suspend fun searchWeathers(query: String): Result<List<Weather>> {
        return weatherRepository.searchWeathers(query)
    }
}