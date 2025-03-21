package francisco.simon.searchbooks.domain.searchBook.usecases

import francisco.simon.searchbooks.core.domain.utils.OperationResult
import francisco.simon.searchbooks.domain.searchBook.entity.Book
import francisco.simon.searchbooks.domain.searchBook.repository.SearchBookRepository
import javax.inject.Inject

class SearchBooksUseCase @Inject constructor(
    private val repository: SearchBookRepository
) {
    suspend operator fun invoke(query: String): OperationResult<List<Book>> {
        return repository.searchBooks(query)
    }
}