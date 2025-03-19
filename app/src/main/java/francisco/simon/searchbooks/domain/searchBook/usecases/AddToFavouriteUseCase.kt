package francisco.simon.searchbooks.domain.searchBook.usecases

import francisco.simon.searchbooks.domain.searchBook.entity.Book
import francisco.simon.searchbooks.domain.searchBook.repository.SearchBookRepository
import javax.inject.Inject

class AddToFavouriteUseCase @Inject constructor (
    private val repository: SearchBookRepository
) {
    suspend operator fun invoke(book: Book) {
        repository.addToFavourite(book)
    }
}