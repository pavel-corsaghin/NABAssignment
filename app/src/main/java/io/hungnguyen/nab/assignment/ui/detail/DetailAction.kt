package io.hungnguyen.nab.assignment.ui.detail


sealed class DetailAction {
    data class GetDetail(val id: Long): DetailAction()
    object ResetEvent: DetailAction()
}
