package francisco.simon.searchbooks.presentation.searchBooks.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import francisco.simon.searchbooks.presentation.getApplicationComponent
import francisco.simon.searchbooks.presentation.searchBooks.SearchBookScreenState
import francisco.simon.searchbooks.presentation.searchBooks.SearchBookViewModel
import kotlinx.coroutines.delay

@Composable
fun SearchBookScreen(modifier: Modifier = Modifier) {
    val viewModelFactory = getApplicationComponent().getViewModelFactory()

    val viewModel: SearchBookViewModel = viewModel(factory = viewModelFactory)
    val screenState = viewModel.state.collectAsState(SearchBookScreenState.Initial)
    LaunchedEffect(key1 = Unit){
        viewModel.onSearchBook("Harry Potter")

    }
    when (val currentState = screenState.value) {
        is SearchBookScreenState.Books -> {
            Log.d("SearchBookScreen", currentState.books.toString())
        }

        SearchBookScreenState.Error -> {
            Log.d("SearchBookScreen", "Error")

        }

        SearchBookScreenState.Initial -> {

        }

        SearchBookScreenState.Loading -> {

        }

        SearchBookScreenState.NothingFound -> {

        }
    }
}