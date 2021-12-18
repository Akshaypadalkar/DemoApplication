package com.example.shaadicom

import android.app.Application
import android.content.Context
import java.lang.ref.WeakReference

class BaseApplication  :Application(){
    companion object {
        private var wApp: WeakReference<BaseApplication>? = WeakReference<BaseApplication>(null)
        val instance: BaseApplication get() = wApp?.get()!!
        var isUpdateDialogDisplayed: Boolean = false
        val context: Context
            get() {
                val app = if (null != wApp) wApp!!.get() else BaseApplication()
                return if (app != null) app.applicationContext else BaseApplication().applicationContext
            }
    }

    override fun onCreate() {
        super.onCreate()
        wApp!!.clear()
        wApp = WeakReference(this@BaseApplication)
    }
}