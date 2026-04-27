package com.bigimpactproject.mysqldeilght

import BdHelper
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatabaseFetchersImpl(
    private val bdHelper: BdHelper
): DatabaseFetchers {
    override suspend fun getNotes(): Flow<List<Notes>>? {
        return bdHelper.withDb { db ->
            db.noteQueries.selectAll().asFlow().mapToList(Dispatchers.Default).map { list ->
                list.map { entity ->
                    Notes(
                        id = entity.id,
                        title = entity.title,
                        description = entity.description
                    )
                }
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
