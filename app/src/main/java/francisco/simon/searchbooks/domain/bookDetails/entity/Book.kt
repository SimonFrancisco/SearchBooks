package francisco.simon.searchbooks.domain.bookDetails.entity

data class Book(
    val id: Int,
    val author: String,
    val isFavourite: Boolean,
    val imageUrl: String,
    val title: String,
    val description: String
)
