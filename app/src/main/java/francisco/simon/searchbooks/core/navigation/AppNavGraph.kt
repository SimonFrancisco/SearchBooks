package francisco.simon.searchbooks.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import francisco.simon.searchbooks.domain.bookDetails.entity.Book

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    searchBookScreenContent: @Composable () -> Unit,
    favouriteBooksContentScreen: @Composable () -> Unit,
    bookDetailsScreenContent: @Composable (Book) -> Unit
) {
    NavHost(
        startDestination = Screen.Home.route,
        navController = navHostController
    ) {
        homeScreenNavGraph(
            searchBookScreenContent = searchBookScreenContent,
            bookDetailsScreenContent = bookDetailsScreenContent
        )
        favouriteBookNavGraph(
            favouriteBooksContentScreen = favouriteBooksContentScreen,
            bookDetailsScreenContent = bookDetailsScreenContent
        )
    }
}