package com.bigimpactproject.mysqldeilght.remote

import com.bigimpactproject.mysqldeilght.Notes
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService(
    private val client: HttpClient = HttpclientProv.client
) {
    suspend fun getPosts(): ResultRemote<List<Notes>> = safeApiCall {
        client.get("/posts").body()
    }
}