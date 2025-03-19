package francisco.simon.searchbooks.domain.searchBook.repository

import francisco.simon.searchbooks.domain.searchBook.entity.Book
import kotlinx.coroutines.flow.Flow

interface SearchBookRepository {

    fun searchBooks(query: String): Flow<List<Book>>

    suspend fun addBookToFavourites(book: Book)

    suspend fun removeBookFromFavourites(book: Book)

}