package francisco.simon.searchbooks.core.domain.utils

sealed interface OperationResult<out T> {
    class Success<T>(val data: T) : OperationResult<T>
    class Error(val message: String) : OperationResult<Nothing>
}