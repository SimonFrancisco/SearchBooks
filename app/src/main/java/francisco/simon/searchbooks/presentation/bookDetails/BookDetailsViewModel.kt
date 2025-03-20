package francisco.simon.searchbooks.presentation.bookDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import francisco.simon.searchbooks.domain.bookDetails.entity.Book
import francisco.simon.searchbooks.domain.bookDetails.usecases.AddToFavouriteUseCase
import francisco.simon.searchbooks.domain.bookDetails.usecases.RemoveFromFavouriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookDetailsViewModel @Inject constructor(
    book: Book,
    private val removeFromFavouriteUseCase: RemoveFromFavouriteUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase
) : ViewModel() {

    private val _screenState: MutableStateFlow<BookDetailsScreenState> =
        MutableStateFlow(BookDetailsScreenState.Initial)

    val screenState = _screenState.onStart {
        emit(BookDetailsScreenState.Initial)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = BookDetailsScreenState.Initial
    )

    init {
        viewModelScope.launch {
            _screenState.emit(
                BookDetailsScreenState.BookDetails(book)
            )
        }

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
        viewModelScope.launch {
            if (book.isFavourite) {
                onRemoveFromFavourite(bookId = book.id)
                _screenState.emit(BookDetailsScreenState.Initial)
                _screenState.emit(BookDetailsScreenState.BookDetails(book.copy(isFavourite = false)))
            } else {
                onAddToFavourite(book)
                _screenState.emit(BookDetailsScreenState.Initial)
                _screenState.emit(BookDetailsScreenState.BookDetails(book.copy(isFavourite = true)))
            }
        }

    }
}