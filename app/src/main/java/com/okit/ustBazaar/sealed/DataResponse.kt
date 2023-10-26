package com.okit.ustBazaar.sealed

sealed class DataResponse<T>(
    var data: T? = null,
    var error: com.okit.ustBazaar.sealed.Error? = null,
) {
    class Success<T>(data: T) : DataResponse<T>(data = data)
    class Error<T>(error: com.okit.ustBazaar.sealed.Error) : DataResponse<T>(error = error)
}