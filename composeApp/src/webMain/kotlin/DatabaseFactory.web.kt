import app.cash.sqldelight.async.coroutines.awaitCreate
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.worker.createDefaultWebWorkerDriver
import com.bigimpactproject.mysqldeilght.MyDatabase

actual class DatabaseFactory {
    actual suspend fun createDatabase(): SqlDriver {
       val driver = createDefaultWebWorkerDriver()
        driver.execute(null, "PRAGMA foreign_keys = ON;", 0)
        MyDatabase.Schema.awaitCreate(driver)
        return driver
    }
}