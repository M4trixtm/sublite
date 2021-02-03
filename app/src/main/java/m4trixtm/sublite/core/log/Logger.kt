package m4trixtm.sublite.core.log

import android.util.Log
import m4trixtm.sublite.BuildConfig

enum class LogType {
    DEBUG,
    INFORMATION,
    ERROR,
    WTF,
    WARN,
    VERBOSE
}

inline fun Any.appLog(
    type: LogType = LogType.DEBUG,
    tag: String = this.javaClass.simpleName,
    throwable: Throwable? = null,
    message: () -> String
) {
    when (type) {
        LogType.DEBUG -> {
            if (BuildConfig.DEBUG) {
                Log.d(tag, message(), throwable)
            }
        }

        LogType.INFORMATION -> {
            Log.i(tag, message(), throwable)
        }

        LogType.ERROR -> {
            Log.e(tag, message(), throwable)
        }

        LogType.WTF -> {
            Log.wtf(tag, message(), throwable)
        }

        LogType.WARN -> {
            Log.w(tag, message(), throwable)
        }

        LogType.VERBOSE -> {
            Log.v(tag, message(), throwable)
        }
    }
}