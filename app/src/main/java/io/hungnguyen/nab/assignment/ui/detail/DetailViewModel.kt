package io.hungnguyen.nab.assignment.ui.detail

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.hungnguyen.nab.assignment.domain.usecase.GetWeatherDetailUseCase
import io.hungnguyen.nab.assignment.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: GetWeatherDetailUseCase
) : BaseViewModel<DetailState, DetailAction>(DetailState()) {

    override fun collectAction(action: DetailAction) {
        when (action) {
            is DetailAction.GetDetail -> getWeatherById(action.id)
            is DetailAction.ResetEvent -> resetEvent()
        }
    }

    private fun resetEvent() {
        viewModelScope.launchSetState { copy(event = DetailEvent.None) }
    }

    private fun getWeatherById(id: Long) {
        viewModelScope.launch {
            launchSetState { copy(isLoading = true) }
            useCase.getWeatherDetail(id)
                .onSuccess {
                    launchSetState { copy(weather = it) }
                }
                .onFailure {
                    launchSetState { copy(event = DetailEvent.ShowError(it.message)) }
                }
            launchSetState { copy(isLoading = false) }
        }
    }
}