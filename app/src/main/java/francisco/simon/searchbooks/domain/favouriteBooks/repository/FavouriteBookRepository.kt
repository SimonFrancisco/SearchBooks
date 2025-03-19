package francisco.simon.searchbooks.domain.favouriteBooks.repository

import francisco.simon.searchbooks.domain.favouriteBooks.entity.Book
import kotlinx.coroutines.flow.Flow

interface FavouriteBookRepository {

    fun getFavouriteBooks(): Flow<List<Book>>

    suspend fun addBookToFavourites(book: Book)

    suspend fun removeBookFromFavourites(book: Book)
}