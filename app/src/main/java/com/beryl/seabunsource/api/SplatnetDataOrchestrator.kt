package com.beryl.seabunsource.api

import com.beryl.seabunsource.api.exceptions.SplatnetMaintenanceException
import com.beryl.seabunsource.api.exceptions.SplatnetUnauthorizedException
import com.beryl.seabunsource.api.requests.SplatnetRequest
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


object SplatnetDataOrchestrator {

    suspend fun requestData(vararg requests: SplatnetRequest<*>): OrchestratorResponse<Int> =
        withContext(Dispatchers.IO) {
            try {
                val gson = GsonBuilder().create()

                val okHttpClient = OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build()

                val retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("https://app.splatoon2.nintendo.net")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

                val splatnet = retrofit.create(Splatnet::class.java)

                //Grab cookie and uniqueId

                requests.forEach {
                    it.setupRequest(splatnet, "", "")
                    it.run()
                }
            } catch (e: SplatnetUnauthorizedException) {
                e.printStackTrace()
                return@withContext OrchestratorResponse.Error(e)
            } catch (e: SplatnetMaintenanceException) {
                e.printStackTrace()
                return@withContext OrchestratorResponse.Error(e)
            } catch (e: IOException) {
                e.printStackTrace()
                return@withContext OrchestratorResponse.Error(e)
            }
            return@withContext OrchestratorResponse.Success(200)
        }

}