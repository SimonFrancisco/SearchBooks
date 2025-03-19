package francisco.simon.searchbooks.core.data.dataSource.network.api

import francisco.simon.searchbooks.core.data.dataSource.network.dto.BookResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("volumes?printType=books")
    suspend fun searchPosts(
        @Query("q") query: String,
        @Query("maxResults") numberOfResults: Int = MAX_NUMBERS_OF_BOOK_PER_QUERY
    ): Response<BookResponseDto>

    companion object {
        private const val MAX_NUMBERS_OF_BOOK_PER_QUERY = 100
    }
}