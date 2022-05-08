package io.hungnguyen.nab.assignment.data.mapper

import io.hungnguyen.nab.assignment.data.model.response.WeatherEntity
import io.hungnguyen.nab.assignment.domain.model.Weather
import javax.inject.Inject

class WeatherMapper @Inject constructor() {

    fun mapFromEntity(entity: WeatherEntity): Weather {
        return Weather(
            date = entity.dt ?: 0,
            temp = entity.temp?.day,
            pressure = entity.pressure,
            humidity = entity.humidity,
            description = entity.description.firstOrNull()?.description
        )
    }
}