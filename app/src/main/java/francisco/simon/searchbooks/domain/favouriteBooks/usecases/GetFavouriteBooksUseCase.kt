package francisco.simon.searchbooks.domain.favouriteBooks.usecases

import francisco.simon.searchbooks.domain.favouriteBooks.entity.Book
import francisco.simon.searchbooks.domain.favouriteBooks.repository.FavouriteBookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouriteBooksUseCase @Inject constructor(
    private val repository: FavouriteBookRepository
) {
    operator fun invoke(): Flow<List<Book>> {
        return repository.getFavouriteBooks()
    }
}