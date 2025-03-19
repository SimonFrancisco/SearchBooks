package francisco.simon.searchbooks.domain.bookDetails.usecases

import francisco.simon.searchbooks.domain.bookDetails.entity.Book
import francisco.simon.searchbooks.domain.bookDetails.repository.BookDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookByIdUseCase @Inject constructor(
    private val repository: BookDetailsRepository
) {
    operator fun invoke(bookId: String): Flow<Book> {
        return repository.getBookById(bookId)
    }
}