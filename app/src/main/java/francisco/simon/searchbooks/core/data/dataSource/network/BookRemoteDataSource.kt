package francisco.simon.searchbooks.core.data.dataSource.network

import francisco.simon.searchbooks.core.data.dataSource.network.api.ApiService
import francisco.simon.searchbooks.core.data.dataSource.network.dto.BookResponseDto
import francisco.simon.searchbooks.core.data.utils.BaseRemoteDataSource
import francisco.simon.searchbooks.core.data.utils.OperationResult

class BookRemoteDataSource(
    private val apiService: ApiService
) : BaseRemoteDataSource() {
    suspend fun searchBook(query: String): OperationResult<BookResponseDto> {
        return safeApiCall { apiService.searchPosts(query) }
    }
}