package francisco.simon.searchbooks.presentation.searchBooks

import francisco.simon.searchbooks.domain.searchBook.entity.Book

sealed class SearchBookScreenState {
    data object Initial : SearchBookScreenState()
    data class Books(
        val books: List<Book>
    ) : SearchBookScreenState()
    data object Loading : SearchBookScreenState()
    data object Error : SearchBookScreenState()
    data object NothingFound : SearchBookScreenState()
}