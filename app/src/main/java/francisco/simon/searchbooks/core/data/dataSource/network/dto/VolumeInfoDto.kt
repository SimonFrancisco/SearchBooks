package francisco.simon.searchbooks.core.data.dataSource.network.dto

import com.google.gson.annotations.SerializedName

data class VolumeInfoDto(
    @SerializedName("title")
    val title: String?,
    @SerializedName("authors")
    val authors: List<String>?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("imageLinks")
    val imageUrl: ImageUrlDto?
)
