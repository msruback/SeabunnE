package com.beryl.seabunsource.api

sealed class OrchestratorResponse<out R> {
    data class Success<out Int>(val successCode: Int) : OrchestratorResponse<Int>()
    data class Error(val exception: Throwable) : OrchestratorResponse<Nothing>()
}