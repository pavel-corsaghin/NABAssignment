package io.hungnguyen.nab.assignment.ui.detail

import io.hungnguyen.nab.assignment.domain.model.Weather

data class DetailState (val isLoading: Boolean = false, val weather: Weather? = null)
