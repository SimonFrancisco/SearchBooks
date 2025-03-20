package francisco.simon.searchbooks.data.bookDetails.repository

import francisco.simon.searchbooks.core.data.dataSource.local.db.BookDao
import francisco.simon.searchbooks.data.bookDetails.mapper.toBook
import francisco.simon.searchbooks.data.bookDetails.mapper.toDb
import francisco.simon.searchbooks.domain.bookDetails.entity.Book
import francisco.simon.searchbooks.domain.bookDetails.repository.BookDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookDetailsRepositoryImpl @Inject constructor(
    private val localDataSource: BookDao
): BookDetailsRepository {

    override fun getBookById(bookId: String): Flow<Book> {
        return localDataSource.getBookById(bookId).map { it.toBook() }
    }

    override suspend fun addToFavourite(book: Book) {
        localDataSource.addToFavourite(book.toDb())
    }

    override suspend fun removeFromFavourite(bookId: String) {
        localDataSource.removeFromFavourite(bookId)
    }
}