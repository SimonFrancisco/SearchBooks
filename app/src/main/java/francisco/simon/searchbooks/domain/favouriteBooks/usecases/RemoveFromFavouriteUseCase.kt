package francisco.simon.searchbooks.domain.favouriteBooks.usecases

import francisco.simon.searchbooks.domain.favouriteBooks.repository.FavouriteBookRepository
import javax.inject.Inject

class RemoveFromFavouriteUseCase @Inject constructor(
    private val repository: FavouriteBookRepository
) {
    suspend operator fun invoke(bookId: String) {
        repository.removeFromFavourite(bookId)
    }
}