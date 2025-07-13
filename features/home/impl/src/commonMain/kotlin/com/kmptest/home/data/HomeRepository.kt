package com.kmptest.home.data

internal class HomeRepository(
        private val api: HomeApi
) {
    suspend fun getAllRockets(): List<Rocket> = api.getAllRockets()
}