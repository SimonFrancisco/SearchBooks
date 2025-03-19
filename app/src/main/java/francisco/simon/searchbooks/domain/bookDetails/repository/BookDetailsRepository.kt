package francisco.simon.searchbooks.domain.bookDetails.repository

import francisco.simon.searchbooks.domain.bookDetails.entity.Book
import kotlinx.coroutines.flow.Flow

interface BookDetailsRepository {

    fun getBookById(bookId: String): Flow<Book>

    suspend fun addBookToFavourites(book: Book)

    suspend fun removeBookFromFavourites(book: Book)

}