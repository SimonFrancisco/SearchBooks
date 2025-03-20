package francisco.simon.searchbooks.presentation.bookDetails

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import francisco.simon.searchbooks.domain.bookDetails.entity.Book
import francisco.simon.searchbooks.presentation.getApplicationComponent
import francisco.simon.searchbooks.ui.theme.BrightRed
import francisco.simon.searchbooks.ui.theme.MediumGray
import francisco.simon.searchbooks.ui.theme.White

@Composable
fun BookDetailsScreen(
    book: Book,
    onBackPressed: () -> Unit
) {
    val component = getApplicationComponent()
        .getBookDetailsFactory().create(book)

    val viewModel: BookDetailsViewModel = viewModel(
        factory = component.getViewModelFactory()
    )
    val screenState = viewModel.screenState.collectAsState(
        BookDetailsScreenState.Initial
    )
    BookDetailsScreenContent(
        screenState = screenState,
        onBackPressed = onBackPressed,
        onFavouriteClicked = {
            viewModel.changeLikeStatus(it)
        }
    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BookDetailsScreenContent(
    screenState: State<BookDetailsScreenState>,
    onBackPressed: () -> Unit,
    onFavouriteClicked: (Book) -> Unit,
) {
    when (val currentState = screenState.value) {
        is BookDetailsScreenState.BookDetails -> {
            Log.d("BookDetailsScreenState",currentState.book.toString())
            Scaffold(
                topBar = {
                    TopAppBar(
                        navigationIcon = {
                            IconButton(onClick = { onBackPressed() }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back button"
                                )
                            }
                        },
                        title = {

                        },
                        actions = {
                            IconButton(
                                onClick = {
                                    onFavouriteClicked(currentState.book)
                                },
                                modifier = Modifier
                                    .padding(8.dp)
                                    .background(White, CircleShape)
                                    .width(24.dp)
                                    .height(24.dp)
                            ) {
                                Icon(
                                    modifier = Modifier.size(16.dp),
                                    imageVector = if (currentState.book.isFavourite) {
                                        Icons.Filled.Favorite
                                    } else {
                                        Icons.Filled.Favorite
                                    },
                                    contentDescription = null,
                                    tint = if (currentState.book.isFavourite) {
                                        BrightRed
                                    } else {
                                        MediumGray
                                    }
                                )
                            }
                        }
                    )
                }
            ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }
        }

        BookDetailsScreenState.Initial -> {

        }
    }
}