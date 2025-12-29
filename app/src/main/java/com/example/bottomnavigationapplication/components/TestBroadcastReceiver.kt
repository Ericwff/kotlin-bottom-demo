package com.example.bottomnavigationapplication.components

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class TestBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action?:return
        if (action == ConnectivityManager.CONNECTIVITY_ACTION) {
            val connectivityManager: ConnectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = connectivityManager.activeNetworkInfo
            if (info!=null && info.isAvailable) {
//                有网络连接的
                val typeName = info.typeName
                Toast.makeText(context,"当前网络连接类型：${typeName}", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context,"当前无网络连接", Toast.LENGTH_LONG).show()
            }
        } else if (action == "com.example.bottomnavigationapplication.TEST_BROADCAST_RECEVIER") {
            Toast.makeText(context,"静态注册广播，接收到了应用自定义的事件", Toast.LENGTH_LONG).show()
        }
    }
}