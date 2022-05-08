package io.hungnguyen.nab.assignment.ui.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.hungnguyen.nab.assignment.domain.usecase.SearchWeatherUseCase
import io.hungnguyen.nab.assignment.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val MIN_SEARCHING_QUERY_LENGTH = 3

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: SearchWeatherUseCase
) : BaseViewModel<HomeViewState, HomeAction>(HomeViewState()) {

    override fun collectAction(action: HomeAction) {
        when (action) {
            is HomeAction.Searching -> onQueryChange(action.query)
            is HomeAction.ResetEvent -> resetEvent()
            is HomeAction.Submit -> submitSearch()
            else -> {}
        }
    }

    private fun onQueryChange(query: String) {
        viewModelScope.launchSetState { copy(query = query) }
    }

    private fun resetEvent() {
        viewModelScope.launchSetState { copy(event = HomeEvent.None) }
    }

    private fun submitSearch() {
        viewModelScope.launch {
            val query = state.value.query.trim()

            // Check for query length
            if (query.length < MIN_SEARCHING_QUERY_LENGTH) {
                launchSetState { copy(event = HomeEvent.ShowQueryTooShortError) }
                return@launch
            }

            // Execute search
            launchSetState { copy(isLoading = true) }
            useCase.searchWeathers(query)
                .onSuccess {
                    launchSetState { copy(weathers = it) }
                }
                .onFailure {
                    launchSetState { copy(event = HomeEvent.ShowError(message = it.message)) }
                }
            launchSetState { copy(isLoading = false) }
        }
    }
}