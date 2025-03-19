package francisco.simon.searchbooks.domain.searchBook.entity

data class Book(
    val id: String,
    val author: String,
    val isFavourite: Boolean,
    val imageUrl: String,
    val title: String,
    val description: String
)
