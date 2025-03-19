package francisco.simon.searchbooks.domain.favouriteBooks.usecases

import francisco.simon.searchbooks.domain.favouriteBooks.entity.Book
import francisco.simon.searchbooks.domain.favouriteBooks.repository.FavouriteBookRepository

class AddBookToFavouritesUseCase(
    private val repository: FavouriteBookRepository
) {
    suspend operator fun invoke(book: Book) {
        repository.addBookToFavourites(book)
    }
}