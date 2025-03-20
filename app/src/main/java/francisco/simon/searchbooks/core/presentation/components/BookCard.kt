package francisco.simon.searchbooks.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import francisco.simon.searchbooks.domain.searchBook.entity.Book
import francisco.simon.searchbooks.ui.theme.BrightRed
import francisco.simon.searchbooks.ui.theme.MediumGray
import francisco.simon.searchbooks.ui.theme.White


@Composable
fun BookCard(
    modifier: Modifier = Modifier,
    book: Book,
    onBookClicked: () -> Unit,
    onFavouriteClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable {
                onBookClicked()
            }
    ) {
        Box(
            modifier = Modifier.clip(RoundedCornerShape(16.dp))
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(160.dp)
                    .height(290.dp),
                model = book.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            IconButton(
                onClick = {
                    onFavouriteClicked()
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
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
        Spacer(modifier = Modifier.height(4.dp))
        Column {
            Text(
                text = book.author.joinToString(", "),
                fontSize = 16.sp,
                color = MediumGray,
                maxLines = 2,
                minLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = book.title,
                fontSize = 16.sp,
                maxLines = 2,
                minLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

