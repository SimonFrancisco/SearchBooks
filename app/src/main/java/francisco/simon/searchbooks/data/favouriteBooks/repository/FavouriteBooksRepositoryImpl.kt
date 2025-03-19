package francisco.simon.searchbooks.data.favouriteBooks.repository

import francisco.simon.searchbooks.core.data.dataSource.local.db.BookDao
import francisco.simon.searchbooks.data.favouriteBooks.mapper.toDb
import francisco.simon.searchbooks.data.favouriteBooks.mapper.toListBook
import francisco.simon.searchbooks.domain.favouriteBooks.entity.Book
import francisco.simon.searchbooks.domain.favouriteBooks.repository.FavouriteBookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouriteBooksRepositoryImpl @Inject constructor(
    private val localDataSource: BookDao
) : FavouriteBookRepository {

    override fun getFavouriteBooks(): Flow<List<Book>> {
        return localDataSource.getFavourites().map {
            it.toListBook()
        }
    }

    override suspend fun addToFavourite(book: Book) {
        localDataSource.addToFavourite(book.toDb())
    }

    override suspend fun removeFromFavourite(bookId: String) {
        localDataSource.removeFromFavourite(bookId)
    }
}