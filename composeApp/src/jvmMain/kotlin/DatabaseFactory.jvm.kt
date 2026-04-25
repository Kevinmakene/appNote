import app.cash.sqldelight.async.coroutines.awaitCreate
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.bigimpactproject.mysqldeilght.MyDatabase
import java.util.Properties

actual class DatabaseFactory {
    actual suspend fun createDatabase(): SqlDriver {
        val driver = JdbcSqliteDriver(
            url = JdbcSqliteDriver.IN_MEMORY,
            properties = Properties().apply {
                setProperty("foreign_keys", "true")
            }
        )
        MyDatabase.Schema.awaitCreate(driver)
        return driver
    }
}