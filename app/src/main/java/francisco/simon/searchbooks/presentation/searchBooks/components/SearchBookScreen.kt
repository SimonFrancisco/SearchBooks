package francisco.simon.searchbooks.presentation.searchBooks.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import francisco.simon.searchbooks.domain.searchBook.entity.Book
import francisco.simon.searchbooks.presentation.getApplicationComponent
import francisco.simon.searchbooks.presentation.searchBooks.SearchBookScreenState
import francisco.simon.searchbooks.presentation.searchBooks.SearchBookViewModel
import francisco.simon.searchbooks.ui.theme.Black

@Composable
fun SearchBookScreen(
    paddingValues: PaddingValues,
    onBookClicked: (Book) -> Unit
) {
    val viewModelFactory = getApplicationComponent().getViewModelFactory()
    val viewModel: SearchBookViewModel = viewModel(factory = viewModelFactory)
    val screenState = viewModel.state.collectAsState(SearchBookScreenState.Initial)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(12.dp))
            SearchBookQuery(
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.height(12.dp))
            SearchBookScreenContent(
                screenState = screenState,
                onBookClicked = onBookClicked,
                onFavouriteClicked = {
                    viewModel.changeLikeStatus(book = it)
                }
            )
        }
    }


}

@Composable
fun SearchBookScreenContent(
    screenState: State<SearchBookScreenState>,
    onFavouriteClicked: (Book) -> Unit,
    onBookClicked: (Book) -> Unit
) {
    when (val currentState = screenState.value) {
        is SearchBookScreenState.Books -> {
            BookGrid(
                books = currentState.books,
                onBookClicked = onBookClicked,
                onFavouriteClicked = onFavouriteClicked
            )
            Log.d("SearchBookScreen", currentState.books.toString())

        }

        SearchBookScreenState.Error -> {
            Log.d("SearchBookScreen", "Error")

        }

        SearchBookScreenState.Initial -> {

        }

        SearchBookScreenState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp), color = Black
                )
            }
        }

        SearchBookScreenState.NothingFound -> {

        }
    }
}

