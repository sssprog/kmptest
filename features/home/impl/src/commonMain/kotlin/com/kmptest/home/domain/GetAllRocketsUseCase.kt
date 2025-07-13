package com.kmptest.home.domain

import com.kmptest.home.data.HomeRepository
import com.kmptest.home.data.Rocket

internal class GetAllRocketsUseCase(private val repository: HomeRepository) {
    suspend operator fun invoke(): List<Rocket> = repository.getAllRockets()
}