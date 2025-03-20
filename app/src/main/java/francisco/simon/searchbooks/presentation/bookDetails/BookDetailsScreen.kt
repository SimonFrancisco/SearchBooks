package francisco.simon.searchbooks.presentation.bookDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import francisco.simon.searchbooks.domain.bookDetails.entity.Book
import francisco.simon.searchbooks.presentation.getApplicationComponent
import francisco.simon.searchbooks.ui.theme.BrightRed
import francisco.simon.searchbooks.ui.theme.LightGray
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

@Composable
private fun BookDetailsScreenContent(
    screenState: State<BookDetailsScreenState>,
    onBackPressed: () -> Unit,
    onFavouriteClicked: (Book) -> Unit,
) {
    when (val currentState = screenState.value) {
        is BookDetailsScreenState.BookDetails -> {
            BookDetails(onBackPressed, onFavouriteClicked, currentState.book)
        }

        BookDetailsScreenState.Initial -> {

        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun BookDetails(
    onBackPressed: () -> Unit,
    onFavouriteClicked: (Book) -> Unit,
    book: Book
) {
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
                            onFavouriteClicked(book)
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .background(White, CircleShape)
                            .width(24.dp)
                            .height(24.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            imageVector = if (book.isFavourite) {
                                Icons.Filled.Favorite
                            } else {
                                Icons.Filled.Favorite
                            },
                            contentDescription = null,
                            tint = if (book.isFavourite) {
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
        ) {
            BookDetailsAfterTopBar(book)
        }
    }
}


@Composable
private fun BookDetailsAfterTopBar(book: Book) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = book.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(fraction = 0.6f)
                .sizeIn(minHeight = 256.dp, maxHeight = 320.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.size(14.dp))
        Text(
            text = book.author.joinToString(", "),
            fontSize = 16.sp,
            color = Color.Gray,
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = book.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.size(8.dp))
        Spacer(Modifier.size(22.dp))
        Card(
            modifier = Modifier.fillMaxSize().weight(1f),
            colors = CardDefaults.cardColors(
                containerColor = LightGray
            ),
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        ) {
            Column(
                Modifier.padding(20.dp)
            ) {
                Text(
                    text = book.description,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.fillMaxHeight(),
                )
            }
        }
    }
}

