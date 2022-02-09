package com.louis.showNft

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Process
import android.util.Log


open class MyApp : Application() {

    companion object {
        lateinit var instance: MyApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        if (!BuildConfig.DEBUG) {
            /** General avoid crash when release version*/
            Thread.setDefaultUncaughtExceptionHandler { thread, error ->
                Log.e("Avoid crash", "Crashed happened : $thread: $error")
            }
        }

        // Publus Reader's internal process should not be initialized like a normal process
        if (!isMainProcess()) return
    }

    private fun isMainProcess(): Boolean {
        val manager =
            getSystemService(Context.ACTIVITY_SERVICE).`as`<ActivityManager>() ?: return true
        val pid = Process.myPid()
        return manager.runningAppProcesses.any { process -> process.pid == pid && process.processName == packageName }
    }
}
