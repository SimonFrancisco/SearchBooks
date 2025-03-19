package francisco.simon.searchbooks.core.data.dataSource.network.dto

import com.google.gson.annotations.SerializedName

data class ImageUrlDto(
    @SerializedName("thumbnail")
    val imageUrl:String
)
