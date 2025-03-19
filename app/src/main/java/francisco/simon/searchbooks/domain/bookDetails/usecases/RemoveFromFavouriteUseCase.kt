package francisco.simon.searchbooks.domain.bookDetails.usecases

import francisco.simon.searchbooks.domain.bookDetails.repository.BookDetailsRepository
import javax.inject.Inject

class RemoveFromFavouriteUseCase @Inject constructor(
    private val repository: BookDetailsRepository
) {
    suspend operator fun invoke(bookId: String) {
        repository.removeFromFavourite(bookId)
    }
}