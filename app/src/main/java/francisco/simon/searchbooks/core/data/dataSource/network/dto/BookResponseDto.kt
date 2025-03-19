package francisco.simon.searchbooks.core.data.dataSource.network.dto

import com.google.gson.annotations.SerializedName

data class BookResponseDto(
    @SerializedName("items")
    val books: List<BookDto>
)
