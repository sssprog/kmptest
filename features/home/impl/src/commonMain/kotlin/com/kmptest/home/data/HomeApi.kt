package com.kmptest.home.data

import com.kmptest.data.ApiException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class HomeApi(
        private val httpClient: HttpClient
) {
    suspend fun getAllRockets(): List<Rocket> {
        try {
            return httpClient.get("https://api.spacexdata.com/v4/rockets").body()
        } catch (e: Throwable) {
            throw ApiException("1", "Error", "Some api error")
        }
    }
}