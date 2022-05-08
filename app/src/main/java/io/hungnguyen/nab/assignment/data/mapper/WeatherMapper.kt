package io.hungnguyen.nab.assignment.data.mapper

import io.hungnguyen.nab.assignment.data.model.response.Weather
import io.hungnguyen.nab.assignment.domain.model.WeatherEntity
import javax.inject.Inject

class WeatherMapper @Inject constructor() {

    fun mapToEntity(item: Weather): WeatherEntity {
        return WeatherEntity(
            date = item.dt ?: 0,
            temp = item.temp?.day,
            pressure = item.pressure,
            humidity = item.humidity,
            description = item.description.firstOrNull()?.description
        )
    }
}