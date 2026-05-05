import android.content.Context
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.bigimpactproject.mysqldeilght.MyDatabase

actual class DatabaseFactory(
    private val context : Context
) {
    actual suspend fun provideDbDriver(schema: SqlSchema<QueryResult.AsyncValue<Unit>>): SqlDriver {
        return AndroidSqliteDriver(
            schema = schema.synchronous(),
            context = context,
            DB_FILE_NAME
        )
    }
}