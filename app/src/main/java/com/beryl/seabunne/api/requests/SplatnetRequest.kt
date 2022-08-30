package com.beryl.seabunne.api.requests

import com.beryl.seabunne.api.Splatnet
import com.beryl.seabunne.api.SplatnetFailedConnectionException
import com.beryl.seabunne.api.SplatnetMaintenanceException
import com.beryl.seabunne.api.SplatnetUnauthorizedException
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
            var response: Response<T>? = null
            try {
                response = call()
            } finally {
                if (response != null && response.isSuccessful) {
                    manageResponse(response)
                } else if (response != null && response.code() == 200) {
                    throw SplatnetMaintenanceException(name)
                } else if (response != null && response.code() == 403) {
                    throw SplatnetUnauthorizedException(name)
                } else {
                    throw SplatnetFailedConnectionException(name)
                }
            }
        }
    }
}
