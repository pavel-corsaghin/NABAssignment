package io.hungnguyen.nab.assignment.data.service

import io.hungnguyen.nab.assignment.data.model.response.WeathersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/forecast/daily")
    suspend fun searchWeathers(
        @Query("q") q: String,
        @Query("cnt") cnt: Int = 7,
        @Query("units") units: String = "metric",
    ): Response<WeathersResponse>
}