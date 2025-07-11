package com.kmptest.data

class ApiException(
        val code: String,
        val title: String,
        val description: String
) : Exception(description)