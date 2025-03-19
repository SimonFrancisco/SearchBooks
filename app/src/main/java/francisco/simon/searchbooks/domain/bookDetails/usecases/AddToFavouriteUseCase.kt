package francisco.simon.searchbooks.domain.bookDetails.usecases

import francisco.simon.searchbooks.domain.bookDetails.entity.Book
import francisco.simon.searchbooks.domain.bookDetails.repository.BookDetailsRepository
import javax.inject.Inject

class AddToFavouriteUseCase @Inject constructor(
    private val repository: BookDetailsRepository
) {
    suspend operator fun invoke(book: Book) {
        repository.addToFavourite(book)
    }
}