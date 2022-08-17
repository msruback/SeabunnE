package com.beryl.seabunsource.api


import com.beryl.seabunsource.data.splatnet2.Schedules
import com.beryl.seabunsource.data.splatnet2.entities.Stage
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface Splatnet {

    @Headers(
        "Accept: */*",
        "Accept-Encoding: gzip, deflate",
        "Accept-Language: en-US",
        "User-Agent: Mozilla/5.0 (Linux; Android 5.1.1; KFDOWI Build/LVY48F; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/55.0.2883.91 Safari/537.36",
        "X-Requested-With: XMLHttpRequest",
        "origin: https://app.splatoon2.nintendo.net",
        "Connection: keep-alive"
    )
    @GET("/")
    fun getHomepage(@Header("X-GameWebToken") token: String): Call<ResponseBody>


    @Headers(
        "Accept-Language: en-US",
        "User-Agent: Mozilla/5.0 (Linux; Android 5.1.1; KFDOWI Build/LVY48F; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/59.0.3071.125 Safari/537.36",
        "X-Requested-With: XMLHttpRequest",
        "x-timezone-offset: 240",
        "Connection: keep-alive",
        "Referer: https://app.splatoon2.nintendo.net/home/schedules"
    )
    @GET("api/schedules")
    suspend fun getSchedules(
        @Header("Cookie") cookie: String,
        @Header("X-Unique-Id") uniqueId: String
    ): Response<Schedules>

    @Headers(
        "Accept-Language: en-US",
        "User-Agent: Mozilla/5.0 (Linux; Android 5.1.1; KFDOWI Build/LVY48F; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/59.0.3071.125 Safari/537.36",
        "X-Requested-With: XMLHttpRequest",
        "x-timezone-offset: 240",
        "Connection: keep-alive",
        "Referer: https://app.splatoon2.nintendo.net/home"
    )
    @GET("api/data/stages")
    fun getStages(
        @Header("Cookie") cookie: String,
        @Header("X-Unique-Id") uniqueId: String
    ): Call<List<Stage>>

}