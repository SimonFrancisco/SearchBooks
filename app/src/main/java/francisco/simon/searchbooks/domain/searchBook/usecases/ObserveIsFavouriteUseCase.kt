package francisco.simon.searchbooks.domain.searchBook.usecases

import francisco.simon.searchbooks.domain.searchBook.repository.SearchBookRepository
import kotlinx.coroutines.flow.Flow

class ObserveIsFavouriteUseCase(
    private val repository: SearchBookRepository
) {
    operator fun invoke(bookId: String): Flow<Boolean> {
        return repository.observeIsFavourite(bookId)
    }
}