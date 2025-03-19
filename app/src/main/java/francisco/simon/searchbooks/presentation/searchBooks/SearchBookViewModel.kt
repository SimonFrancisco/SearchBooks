package francisco.simon.searchbooks.presentation.searchBooks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import francisco.simon.searchbooks.core.domain.utils.OperationResult
import francisco.simon.searchbooks.domain.searchBook.usecases.AddToFavouriteUseCase
import francisco.simon.searchbooks.domain.searchBook.usecases.ObserveIsFavouriteUseCase
import francisco.simon.searchbooks.domain.searchBook.usecases.RemoveFromFavouriteUseCase
import francisco.simon.searchbooks.domain.searchBook.usecases.SearchBooksUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
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

    fun onSearchBook(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _state.emit(SearchBookScreenState.Loading)
            lastQuery.value = query
            if (query.isNotEmpty()) {
                val result = searchBooksUseCase(query)
                if (result is OperationResult.Success) {
                    if (result.data.isEmpty()) {
                        _state.emit(SearchBookScreenState.NothingFound)
                    } else {
                        _state.emit(SearchBookScreenState.Books(result.data))
                    }
                } else if (result is OperationResult.Error) {
                    _state.emit(SearchBookScreenState.Error)
                }
            }
        }
    }

    fun onRepeatSearch() {
        onSearchBook(lastQuery.value)
    }


}