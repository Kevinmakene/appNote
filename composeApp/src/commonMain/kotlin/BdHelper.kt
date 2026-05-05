import com.bigimpactproject.mysqldeilght.MyDatabase
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class BdHelper(
    private val databaseFactory: DatabaseFactory
) {
    private var db : MyDatabase? = null
    private val mutex = Mutex()

    suspend fun <Result : Any> withDb(block: suspend (MyDatabase) -> Result): Result {
        return mutex.withLock {
           if(db == null){
               db = MyDatabase(databaseFactory.provideDbDriver(MyDatabase.Schema))
           }
            return@withLock block(db!!)
        }
    }
}