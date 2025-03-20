package francisco.simon.searchbooks.presentation.main

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import francisco.simon.searchbooks.R
import francisco.simon.searchbooks.core.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    @StringRes
    val titleResId: Int,
    val icon: ImageVector
) {
    data object Home : NavigationItem(
        titleResId = R.string.search_botton_bar_label,
        screen = Screen.Home,
        icon = Icons.Filled.Search
    )

    data object Favourite : NavigationItem(
        titleResId = R.string.favourite_book_bottom_bar_label,
        screen = Screen.HomeFavourite,
        icon = Icons.Filled.Favorite
    )
}