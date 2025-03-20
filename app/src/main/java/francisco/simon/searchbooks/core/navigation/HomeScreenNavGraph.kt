package francisco.simon.searchbooks.core.navigation

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import francisco.simon.searchbooks.domain.bookDetails.entity.Book

fun NavGraphBuilder.homeScreenNavGraph(
    searchBookScreenContent: @Composable () -> Unit,
    bookDetailsScreenContent: @Composable (Book) -> Unit
) {
    navigation(
        startDestination = Screen.SearchBook.route,
        route = Screen.Home.route
    ) {
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

    }
}