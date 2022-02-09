package com.louis.showNft

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.navigateForward(
    resId: Int,
    args: Bundle? = null,
    isSingleTop: Boolean = true,
    cleanTask: Boolean = false
) = runWithoutNavException {
    navigate(
        resId,
        args,
        NavOptions.Builder()
            .setLaunchSingleTop(isSingleTop)
            .apply { if (cleanTask) setPopUpTo(R.id.nav_graph, true) }
            .build()
    )
}

private inline fun runWithoutNavException(block: () -> Unit) =
    try {
        block()
    } catch (e: IllegalArgumentException) {
        Log.w("navigate exception", e)
    } catch (e: ActivityNotFoundException) {
        Log.w("activity not found exception", e)
    }

fun Context.navigateToBrowser(url: String) {
    val uri = Uri.parse(url)
    Intent(Intent.ACTION_VIEW).apply {
        data = uri
        ContextCompat.startActivity(
           this@navigateToBrowser, Intent.createChooser(this, ""), null
        )
    }
}

inline fun <reified T> Any?.`as`(
    isDebug: Boolean = BuildConfig.DEBUG,
    isLogErrorOnStandardOutput: Boolean = false
): T? {
    if (isDebug) return (this as T)

    val result = (this as? T)
    if (result == null) {
        val logString = "object: $this expect: ${T::class}"
        Log.e("Object cast failed", logString)
        if (isLogErrorOnStandardOutput) println(logString)
    }
    return result
}
