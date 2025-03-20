package francisco.simon.searchbooks.presentation.searchBooks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import francisco.simon.searchbooks.core.domain.utils.OperationResult
import francisco.simon.searchbooks.domain.searchBook.entity.Book
import francisco.simon.searchbooks.domain.searchBook.usecases.AddToFavouriteUseCase
import francisco.simon.searchbooks.domain.searchBook.usecases.ObserveIsFavouriteUseCase
import francisco.simon.searchbooks.domain.searchBook.usecases.RemoveFromFavouriteUseCase
import francisco.simon.searchbooks.domain.searchBook.usecases.SearchBooksUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchBookViewModel @Inject constructor(
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
    private val removeFromFavouriteUseCase: RemoveFromFavouriteUseCase,
    private val searchBooksUseCase: SearchBooksUseCase,
    private val observeIsFavouriteUseCase: ObserveIsFavouriteUseCase
) : ViewModel() {

    private var searchJob: Job? = null

    private val _state: MutableStateFlow<SearchBookScreenState> =
        MutableStateFlow(SearchBookScreenState.Initial)

    val state = _state.onStart {
        emit(SearchBookScreenState.Initial)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = SearchBookScreenState.Initial
    )
    private val lastQuery: MutableStateFlow<String> = MutableStateFlow("")

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
            val currentState = _state.value
            if (currentState is SearchBookScreenState.Books) {
                val newList = mutableListOf<Book>()
                newList.addAll(currentState.books)
                newList.replaceAll { oldBook ->
                    if (oldBook == book) {
                        oldBook.copy(isFavourite = !book.isFavourite)
                    } else {
                        oldBook
                    }
                }
                if (book.isFavourite) {
                    onRemoveFromFavourite(bookId = book.id)
                } else {
                    onAddToFavourite(book)
                }
                _state.emit(SearchBookScreenState.Initial)
                _state.emit(SearchBookScreenState.Books(books = newList))
            }
        }

    }

    fun onSearchBook(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            _state.emit(SearchBookScreenState.Loading)
            lastQuery.value = query
            if (query.isNotEmpty()) {
                val result = searchBooksUseCase(query)
                if (result is OperationResult.Success) {
                    if (result.data.isEmpty()) {
                        _state.emit(SearchBookScreenState.NothingFound)
                    } else {
//                        val newListOfBooks = result.data.toMutableList()
//                        for (book in result.data){
//                            if (observeIsFavouriteUseCase(bookId = book.id).first()){
//                                newListOfBooks.replaceAll { oldBook->
//                                    if (oldBook == book) {
//                                        oldBook.copy(isFavourite = true)
//                                    } else {
//                                        oldBook
//                                    }
//                                }
//                            }
//                        }
                        _state.emit(SearchBookScreenState.Books(result.data))
                    }
                } else if (result is OperationResult.Error) {
                    _state.emit(SearchBookScreenState.Error)
                }
            } else {
                _state.emit(SearchBookScreenState.Initial)
            }
        }
    }

    fun onRepeatSearch() {
        onSearchBook(lastQuery.value)
    }


}