package io.hungnguyen.nab.assignment.ui.home

sealed class HomeAction {
    data class Searching(val query: String): HomeAction()
    data class OpenDetail(val id: Long): HomeAction()
    object ResetEvent: HomeAction()
    object Submit: HomeAction()
}