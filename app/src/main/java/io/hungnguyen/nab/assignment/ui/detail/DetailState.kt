package io.hungnguyen.nab.assignment.ui.detail

import io.hungnguyen.nab.assignment.domain.model.WeatherEntity

data class DetailState(
    val isLoading: Boolean = false,
    val weather: WeatherEntity? = null,
    val event: DetailEvent = DetailEvent.None
)

sealed class DetailEvent {
    object None : DetailEvent()
    data class ShowError(val message: String?) : DetailEvent()
}