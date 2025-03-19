package francisco.simon.searchbooks.domain.bookDetails.usecases

import francisco.simon.searchbooks.domain.bookDetails.entity.Book
import francisco.simon.searchbooks.domain.bookDetails.repository.BookDetailsRepository
import kotlinx.coroutines.flow.Flow

class GetBookByIdUseCase(
    private val repository: BookDetailsRepository
) {
    operator fun invoke(bookId: Int): Flow<Book> {
        return repository.getBookById(bookId)
    }
}