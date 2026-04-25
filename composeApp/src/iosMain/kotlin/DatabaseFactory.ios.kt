import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import co.touchlab.sqliter.DatabaseConfiguration
import com.bigimpactproject.mysqldeilght.MyDatabase

actual class DatabaseFactory {
    actual suspend fun createDatabase(): SqlDriver {
      return NativeSqliteDriver(
          schema = MyDatabase.Schema.synchronous(),
          name = DB_FILE_NAME,
          onConfiguration = {conf : DatabaseConfiguration ->
              conf.copy(
                  extendedConfig =
                      DatabaseConfiguration.Extended(
                          foreignKeyConstraints = true
                      )
              )
          }
      )
    }
}