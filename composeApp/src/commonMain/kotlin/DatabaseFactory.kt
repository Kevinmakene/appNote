import app.cash.sqldelight.db.SqlDriver

const val DB_FILE_NAME = "mydatabase"

expect class DatabaseFactory {
    suspend fun createDatabase(): SqlDriver
}