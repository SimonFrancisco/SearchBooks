package francisco.simon.searchbooks.presentation.favouriteBooks.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import francisco.simon.searchbooks.core.presentation.components.BookCard
import francisco.simon.searchbooks.core.presentation.utils.toSearchBook
import francisco.simon.searchbooks.domain.favouriteBooks.entity.Book

@Composable
fun BookGridFavourite(
    books: List<Book>,
    onFavouriteClicked: (Book) -> Unit,
    onBookClicked: (Book) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), contentPadding = PaddingValues(
            top = 16.dp,
            start = 20.dp,
            end = 20.dp,
            bottom = 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp)

    ) {
        items(books, key = { it.id }) { book ->
            BookCard(
                book = book.toSearchBook(),
                onBookClicked = {
                    onBookClicked(book)
                },
                onFavouriteClicked = {
                    onFavouriteClicked(book)
                }
            )
        }
    }

}