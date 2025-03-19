package francisco.simon.searchbooks.core.data.dataSource.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import francisco.simon.searchbooks.core.data.dataSource.local.model.BookDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM books WHERE author=:query")
    fun searchBook(query: String): Flow<List<BookDbModel>>

    @Query("SELECT * FROM books WHERE isFavourite =:isFavourite")
    fun getFavourites(isFavourite: Boolean = true): Flow<List<BookDbModel>>

    @Query("SELECT * FROM books WHERE id=:bookId LIMIT 1")
    fun getBookById(bookId: String): Flow<BookDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBook(book: BookDbModel)

    @Delete
    suspend fun deleteBook(book: BookDbModel)
}