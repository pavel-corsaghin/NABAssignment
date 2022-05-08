package io.hungnguyen.nab.assignment.data.model

class RequestException(val code: Int, message: String) : Throwable(message)