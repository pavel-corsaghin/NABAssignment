package io.hungnguyen.nab.assignment.ui.home

import io.hungnguyen.nab.assignment.domain.model.WeatherEntity

data class HomeViewState (
    val query: String = "",
    val isLoading: Boolean = false,
    val weathers: List<WeatherEntity> = listOf(),
    val event: HomeEvent = HomeEvent.None,
)

sealed class HomeEvent {
    object None: HomeEvent()
    object ShowQueryTooShortError: HomeEvent()
    data class ShowError(val message: String?) : HomeEvent()
}