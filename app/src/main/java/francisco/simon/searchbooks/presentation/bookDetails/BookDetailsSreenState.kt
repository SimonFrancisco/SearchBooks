package francisco.simon.searchbooks.presentation.bookDetails

import francisco.simon.searchbooks.domain.bookDetails.entity.Book

sealed class BookDetailsScreenState {
    data object Initial : BookDetailsScreenState()
    data class BookDetails(
        val book: Book
    ) : BookDetailsScreenState()
}