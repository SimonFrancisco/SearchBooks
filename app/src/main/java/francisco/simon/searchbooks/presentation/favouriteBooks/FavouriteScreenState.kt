package francisco.simon.searchbooks.presentation.favouriteBooks

import francisco.simon.searchbooks.domain.favouriteBooks.entity.Book


sealed class FavouriteScreenState{
    data object Initial : FavouriteScreenState()
    data class Books(
        val books: List<Book>
    ) : FavouriteScreenState()
    data object NothingFound : FavouriteScreenState()
}