package francisco.simon.searchbooks.presentation.searchBooks.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import francisco.simon.searchbooks.R
import francisco.simon.searchbooks.presentation.searchBooks.SearchBookViewModel
import francisco.simon.searchbooks.ui.theme.LightGray
import francisco.simon.searchbooks.ui.theme.MediumGray

@Composable
fun SearchBookQuery(viewModel: SearchBookViewModel) {

    val query = rememberSaveable {
        mutableStateOf("")
    }
    val trailingIconVisibility by remember {
        derivedStateOf {
            query.value.isNotEmpty()
        }
    }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                start = 20.dp,
                end = 20.dp,
            ),
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = LightGray,
            unfocusedContainerColor = LightGray
        ),
        value = query.value,
        onValueChange = { newQuery ->
            query.value = newQuery
            viewModel.onSearchBook(query = query.value)

        },
        placeholder = {
            Text(
                text = stringResource(R.string.search_bar_label),
                fontSize = 18.sp
            )
        },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                tint = MediumGray
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                query.value = ""
                viewModel.onSearchBook(query.value)
            }) {
                if (trailingIconVisibility) {
                    Icon(
                        imageVector = Icons.Outlined.Clear,
                        contentDescription = null,
                        tint = MediumGray
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Go
        ),
        keyboardActions = KeyboardActions(
            onGo = {
                viewModel.onSearchBook(query.value)
            }
        )
    )
}