package net.devrob.escuelapp.models

import androidx.annotation.Keep
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

@Keep
class EscuelApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
    }
}