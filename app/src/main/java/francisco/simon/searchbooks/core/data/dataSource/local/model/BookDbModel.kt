package francisco.simon.searchbooks.core.data.dataSource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookDbModel(
    @PrimaryKey val id: String,
    val author: List<String>,
    val isFavourite: Boolean,
    val imageUrl: String?,
    val title: String,
    val description: String
)