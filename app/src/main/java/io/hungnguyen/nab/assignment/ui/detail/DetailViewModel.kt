package io.hungnguyen.nab.assignment.ui.detail

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.hungnguyen.nab.assignment.domain.usecase.GetWeatherDetailUseCase
import io.hungnguyen.nab.assignment.ui.base.BaseViewModel
import io.hungnguyen.nab.assignment.ui.home.HomeAction
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: GetWeatherDetailUseCase,
    @ApplicationContext private val context: Context
) : BaseViewModel<DetailState, DetailAction>(DetailState()) {

    override fun collectAction(action: DetailAction) {
        when (action) {
            is DetailAction.GetDetail -> getWeatherById(action.id)
        }
    }

    private fun getWeatherById(id: Long) {
        viewModelScope.launch {
            launchSetState { copy(isLoading = true) }
            useCase.getWeatherDetail(id)
                .onSuccess {
                    launchSetState { copy(weather = it) }
                }
                .onFailure {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            launchSetState { copy(isLoading = false) }
        }
    }
}