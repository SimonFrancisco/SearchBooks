package francisco.simon.searchbooks.domain.searchBook.repository

import francisco.simon.searchbooks.core.domain.utils.OperationResult
import francisco.simon.searchbooks.domain.searchBook.entity.Book
import kotlinx.coroutines.flow.Flow

interface SearchBookRepository {

    suspend fun searchBooks(query: String): OperationResult<List<Book>>

    suspend fun addToFavourite(book: Book)

    suspend fun removeFromFavourite(bookId: String)

    fun observeIsFavourite(bookId: String): Flow<Boolean>


}