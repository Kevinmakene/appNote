import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema

const val DB_FILE_NAME = "mydatabase"


expect class DatabaseFactory {
    suspend fun provideDbDriver(
        schema: SqlSchema<QueryResult.AsyncValue<Unit>>
    ): SqlDriver
}



/*
expect class DatabaseFactory {
    suspend fun createDatabase(): SqlDriver
}*/
