package francisco.simon.searchbooks.domain.searchBook.entity

data class Book(
    val id: Int,
    val author: String,
    val isFavourite: Boolean,
    val imageUrl: String,
    val title: String,
)
