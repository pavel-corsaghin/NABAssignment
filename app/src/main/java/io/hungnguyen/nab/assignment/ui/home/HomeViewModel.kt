package io.hungnguyen.nab.assignment.ui.home

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.hungnguyen.nab.assignment.R
import io.hungnguyen.nab.assignment.domain.usecase.SearchWeatherUseCase
import io.hungnguyen.nab.assignment.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val MIN_SEARCHING_QUERY_LENGTH = 3

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: SearchWeatherUseCase,
    @ApplicationContext private val context: Context
) : BaseViewModel<HomeViewState, HomeAction>(HomeViewState()) {

    override fun collectAction(action: HomeAction) {
        when (action) {
            is HomeAction.Searching -> onQueryChange(action.query)
            is HomeAction.Submit -> submitSearch()
            else -> {}
        }
    }

    private fun onQueryChange(query: String) {
        viewModelScope.launchSetState { copy(query = query) }
    }

    private fun submitSearch() {
        viewModelScope.launch {
            val query = state.value.query.trim()

            // Check for query length
            if (query.length < MIN_SEARCHING_QUERY_LENGTH) {
                Toast.makeText(
                    context,
                    context.getText(R.string.err_query_short),
                    Toast.LENGTH_SHORT
                ).show()
                return@launch
            }

            // Execute search
            launchSetState { copy(isLoading = true) }
            useCase.searchWeathers(query)
                .onSuccess {
                    launchSetState { copy(weathers = it) }
                }
                .onFailure {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            launchSetState { copy(isLoading = false) }
        }
    }
}