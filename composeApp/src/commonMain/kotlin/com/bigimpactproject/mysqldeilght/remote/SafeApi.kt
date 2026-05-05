package com.bigimpactproject.mysqldeilght.remote

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.io.IOException

suspend fun <T> safeApiCall(block: suspend () -> T): ResultRemote<T> {
    return try {
        ResultRemote.Success(block())
    } catch (e: RedirectResponseException) {
        ResultRemote.Error("Redirect error: ${e.message}", e.response.status.value)
    }  catch (e: ClientRequestException) {
        ResultRemote.Error("Client error: ${e.message}", e.response.status.value)
    } catch (e: ServerResponseException) {
        ResultRemote.Error("Server error: ${e.message}", e.response.status.value)
    } catch (e: HttpRequestTimeoutException) {
        ResultRemote.Error("Timeout error: ${e.message}")
    } catch (e: IOException) {
        ResultRemote.Error("Network error: ${e.message}")
    } catch (e: Exception) {
        ResultRemote.Error("Unknown error: ${e.message}")
    }
}