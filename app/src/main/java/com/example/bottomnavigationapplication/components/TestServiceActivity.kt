package com.example.bottomnavigationapplication.components

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.bottomnavigationapplication.R
import kotlinx.coroutines.Runnable

class TestServiceActivity : AppCompatActivity() {
    private lateinit var myBInder: TestService2.MyBinder
    lateinit var connection: ServiceConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_test_service1)
//        val startServiceButton = this.findViewById<Button>(R.id.start_service)
//        startServiceButton.setOnClickListener {
//            val intent = Intent(this, TestService1::class.java)
//            startService(intent)
//        }
//        val stopServiceButton = this.findViewById<Button>(R.id.stop_service)
//        stopServiceButton.setOnClickListener {
//            val intent = Intent(this, TestService1::class.java)
//            stopService(intent)
//        }

        setContentView(R.layout.activity_test_service2)

        /*connection = object : ServiceConnection {
            override fun onServiceConnected(
                name: ComponentName?,
                service: IBinder?
            ) {
//                Activity与Service连接成功时回调该方法
                Log.e("TestService2", "-----------Service Connected------------")
                myBInder = service as TestService2.MyBinder
            }

            override fun onServiceDisconnected(name: ComponentName?) {
//                Activity与Service断开连接时回调该方法
                Log.e("TestService2", "-----------Service DisConnected------------")
            }
        }

//        是运行一些 和Activity生命周期相等的后台任务，比如跨进程的通信
        val intent = Intent(this, TestService2::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)*/

        Handler().postDelayed(Runnable {
//            startService(Intent(this@TestServiceActivity, TestService2::class.java))
            Log.e("Build.VERSION.SDK_INT 1","${Build.VERSION.SDK_INT}" )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Log.e("Build.VERSION.SDK_INT 2","${Build.VERSION.SDK_INT}" )
                startForegroundService(Intent(this, TestService2::class.java))
            } else {
                startService(Intent(this, TestService2::class.java))
            }
        }, 70 * 1000)

        val startServiceButton = this.findViewById<Button>(R.id.get_count)
        startServiceButton.setOnClickListener {
//            从服务获取数据
            Log.e("TestService2", "getCount:${myBInder?.getCount()}")
        }
        val stopServiceButton = this.findViewById<Button>(R.id.unbind_service)
        stopServiceButton.setOnClickListener {
            unbindService(connection)
        }
    }

}