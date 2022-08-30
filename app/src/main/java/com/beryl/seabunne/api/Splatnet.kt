package com.beryl.seabunne.api


import com.beryl.seabunne.data.splatnet2.Timeline
import com.beryl.seabunne.data.splatnet2.battles.Schedules
import com.beryl.seabunne.data.splatnet2.battles.entities.Stage
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonSchedule
import okhttp3.ResponseBody
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
    suspend fun getHomepage(@Header("X-GameWebToken") token: String): Response<ResponseBody>

    @Headers(
        "Accept-Language: en-US",
        "User-Agent: Mozilla/5.0 (Linux; Android 5.1.1; KFDOWI Build/LVY48F; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/59.0.3071.125 Safari/537.36",
        "X-Requested-With: XMLHttpRequest",
        "x-timezone-offset: 240",
        "Connection: keep-alive",
        "Referer: https://app.splatoon2.nintendo.net/home/coop"
    )
    @GET("api/coop_schedules")
    suspend fun getSalmonRunSchedule(
        @Header("Cookie") cookie: String?,
        @Header("X-Unique-Id") uniqueId: String?
    ): Response<SalmonSchedule>

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
    suspend fun getStages(
        @Header("Cookie") cookie: String,
        @Header("X-Unique-Id") uniqueId: String
    ): Response<List<Stage>>

    @Headers(
        "Accept-Language: en-US",
        "User-Agent: Mozilla/5.0 (Linux; Android 5.1.1; KFDOWI Build/LVY48F; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/59.0.3071.125 Safari/537.36",
        "X-Requested-With: XMLHttpRequest",
        "x-timezone-offset: 240",
        "Connection: keep-alive",
        "Referer: https://app.splatoon2.nintendo.net/home"
    )
    @GET("api/timeline")
    suspend fun getTimeline(
        @Header("Cookie") Cookie: String?,
        @Header("X-Unique-Id") uniqueId: String?
    ): Response<Timeline>
}
