package francisco.simon.searchbooks.presentation.favouriteBooks

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import francisco.simon.searchbooks.R
import francisco.simon.searchbooks.domain.favouriteBooks.entity.Book
import francisco.simon.searchbooks.presentation.getApplicationComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteBookScreen(onBookClicked: (Book) -> Unit, onBackPressed: () -> Unit) {

    val viewModelFactory = getApplicationComponent().getViewModelFactory()
    val viewModel: FavouriteBooksViewModel = viewModel(factory = viewModelFactory)
    val screenState = viewModel.state.collectAsState(FavouriteScreenState.Initial)
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.favourite_topBar_label),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }, navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back button"
                        )
                    }
                })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            FavouriteBookScreenContent(
                screenState = screenState,
                onBookClicked = onBookClicked,
                onFavouriteClicked = {
                    viewModel.changeLikeStatus(it)
                }
            )
        }

    }
}

@Composable
fun FavouriteBookScreenContent(
    screenState: State<FavouriteScreenState>,
    onFavouriteClicked: (Book) -> Unit,
    onBookClicked: (Book) -> Unit
) {
    when (val currentState = screenState.value) {
        is FavouriteScreenState.Books -> {
            BookGridFavourite(
                books = currentState.books,
                onBookClicked = onBookClicked,
                onFavouriteClicked = onFavouriteClicked
            )
            Log.d("FavouriteScreenState", currentState.books.toString())

        }

        FavouriteScreenState.Initial -> {

        }

        FavouriteScreenState.NothingFound -> {

        }
    }
}