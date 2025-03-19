package francisco.simon.searchbooks.domain.searchBook.usecases

import francisco.simon.searchbooks.domain.searchBook.entity.Book
import francisco.simon.searchbooks.domain.searchBook.repository.SearchBookRepository

class AddBookToFavouritesUseCase(
    private val repository: SearchBookRepository
) {
    suspend operator fun invoke(book: Book) {
        repository.addBookToFavourites(book)
    }
}