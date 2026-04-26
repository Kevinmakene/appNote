package com.bigimpactproject.mysqldeilght

import BdHelper

class DatabaseFetchersImpl(
    private val bdHelper: BdHelper
): DatabaseFetchers {
    override suspend fun getNotes(): List<Notes> {
        return bdHelper.withDb { db ->
            db.noteQueries.selectAll()
        }
    }

    override suspend fun insertNote(title: String, description: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNote(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun updateNote(
        id: Int,
        title: String,
        description: String
    ) : Notes{
        TODO("Not yet implemented")
    }

}