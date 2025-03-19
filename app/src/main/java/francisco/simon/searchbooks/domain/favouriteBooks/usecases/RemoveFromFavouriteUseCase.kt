package francisco.simon.searchbooks.domain.favouriteBooks.usecases

import francisco.simon.searchbooks.domain.favouriteBooks.repository.FavouriteBookRepository

class RemoveFromFavouriteUseCase(
    private val repository: FavouriteBookRepository
) {
    suspend operator fun invoke(bookId: String) {
        repository.removeFromFavourite(bookId)
    }
}