package com.bigimpactproject.mysqldeilght

import BdHelper

class DatabaseFetchersImpl(
    private val bdHelper: BdHelper
): DatabaseFetchers {
    override suspend fun getNotes(): List<Notes> {
        return bdHelper.withDb { db ->
            db.noteQueries.selectAll().executeAsList().map{
                Notes(
                    id = it.id,
                    title = it.title,
                    body = it.description
                )
            }
        }
    }

    override suspend fun insertNote(title: String, description: String) {
       bdHelper.withDb { db ->
           db.noteQueries.insertNote(
               title = title,
               description = description
           )
       }
    }

    override suspend fun deleteNote(id: Long) {
       bdHelper.withDb { db->
           db.noteQueries.deleteNote(id)

       }
    }

    override suspend fun updateNote(
        id: Int,
        title: String,
        description: String
    ) {
       return bdHelper.withDb { db->
              db.noteQueries.updateNote(
                title = title,
                description = description,
                id = id.toLong()
              )
                  }
    }

}
