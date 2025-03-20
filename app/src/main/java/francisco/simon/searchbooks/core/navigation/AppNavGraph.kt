package francisco.simon.searchbooks.core.navigation

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import francisco.simon.searchbooks.domain.bookDetails.entity.Book

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    searchBookScreenContent: @Composable () -> Unit,
    favouriteBooksContentScreen: @Composable () -> Unit,
    bookDetailsScreenContent: @Composable (Book) -> Unit
) {
    NavHost(
        startDestination = Screen.SearchBook.route,
        navController = navHostController,
    ) {
//        homeScreenNavGraph(
//            searchBookScreenContent = searchBookScreenContent,
//            bookDetailsScreenContent = bookDetailsScreenContent
//        )
//        favouriteBookNavGraph(
//            favouriteBooksContentScreen = favouriteBooksContentScreen,
//            bookDetailsScreenContent = bookDetailsScreenContent
//        )
        composable(Screen.SearchBook.route) {
            searchBookScreenContent()
        }
        composable(
            route = Screen.BookDetails.route,
            arguments = listOf(navArgument(Screen.KEY_BOOK) {
                type = Book.navigationType
            })
        ) {
            val book = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                it.arguments?.getParcelable(Screen.KEY_BOOK)
            } else {
                it.arguments?.getParcelable(Screen.KEY_BOOK, Book::class.java)
            } ?: throw RuntimeException("Args is null")
            bookDetailsScreenContent(book)
        }
        composable(Screen.Favourite.route) {
            favouriteBooksContentScreen()
        }
    }
}