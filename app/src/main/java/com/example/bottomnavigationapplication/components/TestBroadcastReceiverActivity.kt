package com.example.bottomnavigationapplication.components

import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class TestBroadcastReceiverActivity : AppCompatActivity() {
    private lateinit var receiver: TestBroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        receiver = TestBroadcastReceiver()
//创建广播过滤器，指定只接收android.net.conn.CONNECTIVITY_CHANGE的广播事件
        val intentFilter = IntentFilter()
//        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
//        registerReceiver(receiver,intentFilter)
        intentFilter.addAction("com.example.bottomnavigationapplication.TEST_BROADCAST_RECEVIER")
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, intentFilter)
        Handler().postDelayed(Runnable{
            LocalBroadcastManager.getInstance(this).sendBroadcast(Intent("com.example.bottomnavigationapplication.TEST_BROADCAST_RECEVIER"))
        },2000)

        /*val intent = Intent()
        intent.action = "com.example.bottomnavigationapplication.TEST_BROADCAST_RECEVIER"
        intent.component = ComponentName(
            packageName,
            "com.example.bottomnavigationapplication.components.TestBroadcastReceiver"
        )
        sendBroadcast(intent)*/
    }

    override fun onDestroy() {
        super.onDestroy()
//        unregisterReceiver(receiver) // 必须要在onDestroy时反注册，否则会内存泄漏
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
    }
}