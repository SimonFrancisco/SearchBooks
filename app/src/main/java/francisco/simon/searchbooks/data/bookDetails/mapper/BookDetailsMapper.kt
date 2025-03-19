package francisco.simon.searchbooks.data.bookDetails.mapper

import francisco.simon.searchbooks.core.data.dataSource.local.model.BookDbModel
import francisco.simon.searchbooks.domain.bookDetails.entity.Book

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

internal fun BookDbModel.toBook(): Book {
    return Book(
        id = id,
        author = author,
        imageUrl = imageUrl,
        isFavourite = isFavourite,
        description = description,
        title = title
    )

}