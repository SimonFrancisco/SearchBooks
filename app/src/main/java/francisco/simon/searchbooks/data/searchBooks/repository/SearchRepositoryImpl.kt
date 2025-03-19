package francisco.simon.searchbooks.data.searchBooks.repository

import francisco.simon.searchbooks.core.data.dataSource.local.db.BookDao
import francisco.simon.searchbooks.core.data.dataSource.network.BookRemoteDataSource
import francisco.simon.searchbooks.core.domain.utils.OperationResult
import francisco.simon.searchbooks.core.domain.utils.flatMapIfSuccess
import francisco.simon.searchbooks.core.domain.utils.toSuccessResult
import francisco.simon.searchbooks.data.searchBooks.mappers.toDb
import francisco.simon.searchbooks.data.searchBooks.mappers.toEntity
import francisco.simon.searchbooks.domain.searchBook.entity.Book
import francisco.simon.searchbooks.domain.searchBook.repository.SearchBookRepository
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(
    private val remoteDataSource: BookRemoteDataSource,
    private val localDataSource: BookDao
) : SearchBookRepository {

    override suspend fun searchBooks(query: String): OperationResult<List<Book>> {
        return remoteDataSource.searchBook(query).flatMapIfSuccess { bookResponseDto ->
            bookResponseDto.books.map {
                    it.toEntity()
            }.toSuccessResult()
        }
    }

    override suspend fun addToFavourite(book: Book) {
        localDataSource.addToFavourite(book.toDb())
    }

    override suspend fun removeFromFavourite(bookId: String) {
        localDataSource.removeFromFavourite(bookId)
    }

    override fun observeIsFavourite(bookId: String): Flow<Boolean> {
        return localDataSource.observeIsFavourite(bookId)
    }


}