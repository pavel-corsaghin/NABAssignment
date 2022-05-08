package io.hungnguyen.nab.assignment.domain.model

data class WeatherEntity(
    val date: Long,
    val temp: Double?,
    val pressure: Int?,
    val humidity: Int?,
    val description: String?
) {
    val id: Long
        get() = date
}