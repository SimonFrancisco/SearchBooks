package francisco.simon.searchbooks.domain.searchBook.usecases

import francisco.simon.searchbooks.domain.searchBook.entity.Book
import francisco.simon.searchbooks.domain.searchBook.repository.SearchBookRepository
import kotlinx.coroutines.flow.Flow

class SearchBooksUseCase(
    private val repository: SearchBookRepository
) {
    operator fun invoke(query: String):Flow<List<Book>>{
        return repository.searchBooks(query)
    }
}