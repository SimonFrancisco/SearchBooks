package francisco.simon.searchbooks.core.data.dataSource.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import francisco.simon.searchbooks.core.data.dataSource.local.model.BookDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM books WHERE isFavourite =:isFavourite")
    fun getFavourites(isFavourite: Boolean = true): Flow<List<BookDbModel>>

    @Query("SELECT * FROM books WHERE id=:bookId LIMIT 1")
    fun getBookById(bookId: String): Flow<BookDbModel>

    @Query("SELECT EXISTS (SELECT * FROM books WHERE id=:bookId LIMIT 1)")
    fun observeIsFavourite(bookId:String):Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavourite(book: BookDbModel)

    @Query("DELETE FROM books WHERE id=:bookId")
    suspend fun removeFromFavourite(bookId: String)



}