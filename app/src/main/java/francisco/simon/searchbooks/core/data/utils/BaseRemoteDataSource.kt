package francisco.simon.searchbooks.core.data.utils

import kotlinx.coroutines.ensureActive
import retrofit2.Response
import kotlin.coroutines.coroutineContext

abstract class BaseRemoteDataSource {
    suspend fun <T> safeApiCall(
        api: suspend () -> Response<T>
    ): OperationResult<T> {
        return try {
            val response = api()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    OperationResult.Success(body)
                } ?: getErrorResult(
                    errorMessage = "Body is empty"
                )
            } else {
                getErrorResult(
                    errorMessage = "${response.code()} ${response.message()}"
                )
            }
        } catch (e: Exception) {
            coroutineContext.ensureActive()
            getErrorResult(
                errorMessage = "Api call failed: ${e.message.toString()}"
            )
        }

    }

    private fun getErrorResult(errorMessage: String): OperationResult.Error {
        return OperationResult.Error(errorMessage)
    }
}