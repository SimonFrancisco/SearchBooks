package francisco.simon.searchbooks.domain.bookDetails.usecases

import francisco.simon.searchbooks.domain.bookDetails.entity.Book
import francisco.simon.searchbooks.domain.bookDetails.repository.BookDetailsRepository

class RemoveBookFromFavouritesUseCase(
    private val repository: BookDetailsRepository
) {
    suspend operator fun invoke(book: Book) {
        repository.removeBookFromFavourites(book)
    }
}