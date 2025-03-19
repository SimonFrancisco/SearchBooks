package francisco.simon.searchbooks.core.data.dataSource.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import francisco.simon.searchbooks.core.data.dataSource.local.model.BookDbModel
import francisco.simon.searchbooks.core.data.dataSource.local.typeConverters.AuthorsTypeConverter

@TypeConverters(AuthorsTypeConverter::class)
@Database(entities = [BookDbModel::class], version = 1, exportSchema = false)
abstract class BookDataBase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {

        private const val DB_NAME = "BookDataBase"
        private val LOCK = Any()
        private var INSTANCE: BookDataBase? = null

        fun getInstance(context: Context): BookDataBase {

            INSTANCE?.let {
                return it
            }

            synchronized(LOCK) {

                INSTANCE?.let {
                    return it
                }

                val database = Room.databaseBuilder(
                    context = context,
                    klass = BookDataBase::class.java,
                    name = DB_NAME
                ).build().also {
                    INSTANCE = it
                }

                return database
            }

        }
    }
}