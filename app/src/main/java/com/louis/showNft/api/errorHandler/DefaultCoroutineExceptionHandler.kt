package com.louis.showNft.api.errorHandler

import android.util.Log
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler

/**
 * The [CoroutineExceptionHandler] with log.
 */
val logExceptionHandler = CoroutineExceptionHandler { _, exception ->
    if (exception is CancellationException) {
        Log.e(
            "logExceptionHandler",
            "coroutine was cancelled (this is not a serious error)",
            exception
        )
    } else {
        Log.e("logExceptionHandler", "caught an unexpected exception", exception)
    }
    for (e in exception.suppressed ?: emptyArray()) {
        Log.e("logExceptionHandler", "with this suppressed exception", e)
    }
}
