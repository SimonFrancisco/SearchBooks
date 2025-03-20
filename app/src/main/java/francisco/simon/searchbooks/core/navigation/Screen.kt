package francisco.simon.searchbooks.core.navigation

import android.net.Uri
import com.google.gson.Gson
import francisco.simon.searchbooks.domain.bookDetails.entity.Book

sealed class Screen(
    val route: String
) {
    data object Home : Screen(ROUTE_HOME)
    data object HomeFavourite:Screen(ROUTE_HOME_FAVOURITE)
    data object SearchBook : Screen(ROUTE_SEARCH_BOOK)
    data object Favourite : Screen(ROUTE_FAVOURITE)
    data object BookDetails : Screen(ROUTE_BOOK_DETAILS) {

        private const val ROUTE_FOR_ARGS = "book_details"
        fun getRouteWithArgs(book: Book): String {
            val bookJson = Gson().toJson(book)
            return "$ROUTE_FOR_ARGS/${bookJson.encode()}"
        }
    }
    companion object {
        const val KEY_BOOK = "book"
        const val ROUTE_HOME = "home"
        const val ROUTE_BOOK_DETAILS = "book_details/{$KEY_BOOK}"
        const val ROUTE_SEARCH_BOOK = "search_book"
        const val ROUTE_FAVOURITE = "favourite"
        const val ROUTE_HOME_FAVOURITE ="home_favourite"
    }
}

fun String.encode(): String {
    return Uri.encode(this)
}