package com.example.bottomnavigationapplication.components

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class TestService1 : Service() {
    private val TAG = "TestService1"
    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate")
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.e(TAG, "onBind")
        return null
    }

    /**
     * 对于使用startService的方式而言，onStartCommand就是我们用于做后台任务的地方
     * 吐过我们多次调用startService，会直接回调onStartCommand，而不再回调onCreate
     *
     * 这种启动的服务，它的生命周期跟应用程序的生命周期一样长，只要应用程序不被杀死，服务就会一直运行着，除非我们调用stopService
     *
     * startService一般来说是用于创建一个长时间持续运行的后台任务的时候 才会使用，比如socket，文件上传下载服务
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e(TAG, "onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy")
    }
}