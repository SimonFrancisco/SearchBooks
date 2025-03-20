package francisco.simon.searchbooks.core.presentation.utils

import francisco.simon.searchbooks.domain.searchBook.entity.Book


fun Book.toBookDetails(): francisco.simon.searchbooks.domain.bookDetails.entity.Book {
    return francisco.simon.searchbooks.domain.bookDetails.entity.Book(
        id = id,
        title = title,
        imageUrl = imageUrl,
        author = author,
        isFavourite = isFavourite,
        description = description
    )
}

fun francisco.simon.searchbooks.domain.favouriteBooks.entity.Book.toBookDetails(): francisco.simon.searchbooks.domain.bookDetails.entity.Book {
    return francisco.simon.searchbooks.domain.bookDetails.entity.Book(
        id = id,
        title = title,
        imageUrl = imageUrl,
        author = author,
        isFavourite = isFavourite,
        description = description
    )
}

fun francisco.simon.searchbooks.domain.favouriteBooks.entity.Book.toSearchBook(): Book {
    return Book(
        id = id,
        title = title,
        imageUrl = imageUrl,
        author = author,
        isFavourite = isFavourite,
        description = description
    )
}

fun Book.toFavouriteBook(): francisco.simon.searchbooks.domain.favouriteBooks.entity.Book {
    return francisco.simon.searchbooks.domain.favouriteBooks.entity.Book(
        id = id,
        title = title,
        imageUrl = imageUrl,
        author = author,
        isFavourite = isFavourite,
        description = description
    )
}
