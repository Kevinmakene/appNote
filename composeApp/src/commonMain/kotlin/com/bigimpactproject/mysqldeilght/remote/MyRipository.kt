package com.bigimpactproject.mysqldeilght.remote

import com.bigimpactproject.mysqldeilght.DatabaseFetchers
import com.bigimpactproject.mysqldeilght.Notes

class MyRipository(
    private val apiService: ApiService,
    private val databaseFetchers: DatabaseFetchers
) {
    suspend fun getElement(): List<Notes>{
        var response = databaseFetchers.getNotes()
        println("Response from database: $response")
        if (response == null || response.isEmpty()) {
            val apiResponse = apiService.getPosts()
             response = when (apiResponse){
                 is ResultRemote.Success -> {
                        val data = apiResponse.data
                        data.forEach {
                            databaseFetchers.insertNote(it.title,it.body)
                        }
                     println("MyData : $data")
                     data
                 }
                 is ResultRemote.Error -> {
                     // Handle error, maybe log it or return an empty list
                     println("Error fetching data: ${apiResponse.message}, Code: ${apiResponse.code}")
                     emptyList()

                 }
             }
        }
        return response
    }

    suspend fun getForWeb(): List<Notes>{
        val apiResponse = apiService.getPosts()
        return when( apiResponse){
            is ResultRemote.Success -> {
                val data = apiResponse.data
                println("MyData : $data")
                data
            }
            is ResultRemote.Error -> {
                // Handle error, maybe log it or return an empty list
                println("Error fetching data: ${apiResponse.message}, Code: ${apiResponse.code}")
                emptyList()

            }
        }
    }




}