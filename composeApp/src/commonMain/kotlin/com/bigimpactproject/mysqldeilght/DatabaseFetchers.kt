package com.bigimpactproject.mysqldeilght

import kotlinx.coroutines.flow.Flow

interface DatabaseFetchers {
    suspend fun getNotes(): Flow<List<Notes>>?
    suspend fun insertNote(title: String, description: String)
    suspend fun deleteNote(id: Long)
    suspend fun updateNote(id: Int, title: String, description: String)
}