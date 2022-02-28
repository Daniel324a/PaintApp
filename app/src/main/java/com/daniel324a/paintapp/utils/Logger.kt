package com.daniel324a.paintapp.utils

import android.util.Log

final class Logger {

    companion object CustomLog {
        fun avocado(data: Any) {
            Log.d("LOG \uD83E\uDD51 ➡️", "" + data.toString())
        }
    }

}