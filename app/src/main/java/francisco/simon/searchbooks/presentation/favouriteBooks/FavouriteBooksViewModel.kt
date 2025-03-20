package francisco.simon.searchbooks.presentation.favouriteBooks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import francisco.simon.searchbooks.domain.favouriteBooks.entity.Book
import francisco.simon.searchbooks.domain.favouriteBooks.usecases.AddToFavouriteUseCase
import francisco.simon.searchbooks.domain.favouriteBooks.usecases.GetFavouriteBooksUseCase
import francisco.simon.searchbooks.domain.favouriteBooks.usecases.RemoveFromFavouriteUseCase
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouriteBooksViewModel @Inject constructor(
    private val getFavouriteBooksUseCase: GetFavouriteBooksUseCase,
    private val removeFromFavouriteUseCase: RemoveFromFavouriteUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase
) : ViewModel() {

    val state = getFavouriteBooksUseCase().map {
        if (it.isEmpty()) {
            FavouriteScreenState.NothingFound as FavouriteScreenState
        } else {
            FavouriteScreenState.Books(it) as FavouriteScreenState

        }
    }.onStart {
        emit(FavouriteScreenState.Initial)
    }

    private fun onRemoveFromFavourite(bookId: String) {
        viewModelScope.launch {
            removeFromFavouriteUseCase(bookId)
        }
    }

    private fun onAddToFavourite(book: Book) {
        viewModelScope.launch {
            addToFavouriteUseCase(book)
        }
    }

    fun changeLikeStatus(book: Book) {
        if (book.isFavourite) {
            onRemoveFromFavourite(bookId = book.id)
        } else {
            onAddToFavourite(book)
        }
    }

}
