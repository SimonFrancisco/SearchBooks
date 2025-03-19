package francisco.simon.searchbooks.core.data.dataSource.network.dto

import com.google.gson.annotations.SerializedName

data class BookDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("volumeInfo")
    val volumeInfo: VolumeInfoDto
)