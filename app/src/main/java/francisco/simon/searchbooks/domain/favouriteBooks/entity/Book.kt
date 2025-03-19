package francisco.simon.searchbooks.domain.favouriteBooks.entity

data class Book(
    val id: String,
    val author: List<String>,
    val isFavourite: Boolean,
    val imageUrl: String?,
    val title: String,
    val description: String
)
