package com.beryl.seabunne.api.requests

import com.beryl.seabunne.api.Splatnet
import com.beryl.seabunne.api.exceptions.SplatnetMaintenanceException
import com.beryl.seabunne.api.exceptions.SplatnetUnauthorizedException
import retrofit2.Response

abstract class SplatnetRequest<T> {
    abstract val name: String

    lateinit var splatnet: Splatnet
    lateinit var cookie: String
    lateinit var uniqueId: String

    protected abstract suspend fun call(): Response<T>

    open fun setupRequest(splatnet: Splatnet, cookie: String, uniqueId: String) {
        this.splatnet = splatnet
        this.cookie = cookie
        this.uniqueId = uniqueId
    }

    protected abstract fun manageResponse(response: Response<T>)

    open fun shouldUpdate(): Boolean = true

    suspend fun run() {
        if (shouldUpdate()) {
            val response = call()
            if (response.isSuccessful) {
                manageResponse(response)
            } else if (response.code() == 200) {
                throw SplatnetMaintenanceException(name)
            } else {
                throw SplatnetUnauthorizedException(name)
            }
        }
    }

}