package francisco.simon.searchbooks.data.searchBooks.mappers

import francisco.simon.searchbooks.core.data.dataSource.local.model.BookDbModel
import francisco.simon.searchbooks.core.data.dataSource.network.dto.BookDto
import francisco.simon.searchbooks.domain.searchBook.entity.Book

internal fun Book.toDb(): BookDbModel {
    return BookDbModel(
        id = id,
        author = author,
        imageUrl = imageUrl,
        isFavourite = true,
        description = description,
        title = title
    )
}


internal fun BookDto.toEntity(): Book {
    return Book(
        id = id,
        author = volumeInfo.authors.joinToString(","),
        isFavourite = false,
        imageUrl = volumeInfo.imageUrl.imageUrl,
        description = volumeInfo.description,
        title = volumeInfo.title
    )
}

