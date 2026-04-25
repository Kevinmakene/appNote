import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.bigimpactproject.mysqldeilght.MyDatabase

actual class DatabaseFactory(
   private val context: Context
) {
    actual suspend fun createDatabase(): SqlDriver {
        val schema = MyDatabase.Schema
        return AndroidSqliteDriver(
            schema = schema.synchronous(),
            context = context,
            name = DB_FILE_NAME,
            callback = object : AndroidSqliteDriver.Callback(schema.synchronous()) {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onConfigure(db)
                    db.setForeignKeyConstraintsEnabled(true)
                }
            }

        )
    }
}