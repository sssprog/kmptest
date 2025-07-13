package com.kmptest.home.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rocket(
        @SerialName("name")
        val name: String,

        @SerialName("country")
        val country: String,

        @SerialName("company")
        val company: String,

        @SerialName("description")
        val description: String,

        @SerialName("cost_per_launch")
        val cost: Int,

        @SerialName("flickr_images")
        val rocketsImages : List<String>
)