package francisco.simon.searchbooks.domain.favouriteBooks.usecases

import francisco.simon.searchbooks.domain.favouriteBooks.entity.Book
import francisco.simon.searchbooks.domain.favouriteBooks.repository.FavouriteBookRepository
import francisco.simon.searchbooks.domain.searchBook.repository.SearchBookRepository

class AddToFavouriteUseCase(
    private val repository: FavouriteBookRepository
) {
    suspend operator fun invoke(book: Book) {
        repository.addToFavourite(book)
    }
}