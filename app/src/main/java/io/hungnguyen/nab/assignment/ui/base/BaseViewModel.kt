package io.hungnguyen.nab.assignment.ui.base

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseViewModel<S, A>(initialState: S) : StateViewModel<S>(initialState) {

    private val pendingActions = MutableSharedFlow<A>()

    init {
        viewModelScope.launch {
            pendingActions.collect { action ->
                collectAction(action)
            }
        }
    }

    abstract fun collectAction(action: A)

    fun submitAction(action: A) {
        viewModelScope.launch {
            pendingActions.emit(action)
        }
    }
}