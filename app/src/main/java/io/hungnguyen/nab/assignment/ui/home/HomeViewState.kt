package io.hungnguyen.nab.assignment.ui.home

import io.hungnguyen.nab.assignment.domain.model.Weather

data class HomeViewState (
    val query: String = "",
    val isLoading: Boolean = false,
    val weathers: List<Weather> = listOf(),
    val error: String? = null,
)
