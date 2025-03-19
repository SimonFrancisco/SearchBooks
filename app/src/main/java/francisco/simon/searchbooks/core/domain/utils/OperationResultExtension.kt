package francisco.simon.searchbooks.core.domain.utils


fun <In, Out> OperationResult<In>.flatMapIfSuccess(
    block: (In) -> OperationResult<Out>
): OperationResult<Out> {
    return when (this) {
        is OperationResult.Success -> block(this.data)
        is OperationResult.Error -> OperationResult.Error(this.message)
    }
}
fun <T> T.toSuccessResult(): OperationResult.Success<T> = OperationResult.Success(this)