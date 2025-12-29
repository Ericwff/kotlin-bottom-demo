package com.example.bottomnavigationapplication.components

import android.annotation.SuppressLint
import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.Runnable

class TestService2 : Service() {
    private val TAG = "TestService2"
    private var count = 0
    private var quit = false
    @SuppressLint("ForegroundServiceType")
    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate方法被调用！")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notification = Notification.Builder(applicationContext,"channel_id").build()
            startForeground(1,notification)
        }

//创建一个线程动态的修改count的值
        Thread(Runnable {
            while(true) {
                if(quit){
                    break
                }
                Thread.sleep(1000)
                count++
            }
        }).start()
    }

    private val binder = MyBinder()

    inner class MyBinder : Binder() {
        fun getCount(): Int {
            return count
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.e(TAG, "onBind方法被调用！")
        return binder
    }

    /**
     * 通过bindService启动的服务，onStartCommand是不会被触发的
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand方法被调用！")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e(TAG, "onUnbind方法被调用！")
        quit = true
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy方法被调用！")
    }
}