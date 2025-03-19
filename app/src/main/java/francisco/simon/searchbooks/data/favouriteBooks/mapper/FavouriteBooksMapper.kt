package francisco.simon.searchbooks.data.favouriteBooks.mapper

import francisco.simon.searchbooks.core.data.dataSource.local.model.BookDbModel
import francisco.simon.searchbooks.domain.favouriteBooks.entity.Book

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

internal fun List<BookDbModel>.toListBook():List<Book>{
    return this.map {
        it.toBook()
    }
}
