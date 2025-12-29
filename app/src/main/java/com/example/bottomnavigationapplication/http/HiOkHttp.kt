package com.example.bottomnavigationapplication.http

import android.content.Context
import android.os.Environment
import android.util.Log
import android.widget.Toast
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import java.util.concurrent.TimeUnit
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import java.io.File
import com.example.bottomnavigationapplication.BuildConfig // ← 显式导入更安全
private const val baseUrl = BuildConfig.BASE_URL

object HiOkHttp {
    private val client: OkHttpClient

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        client = OkHttpClient.Builder() // builder构造者设计模式
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(LoggingInterceptor())
            .build()
    }


    //    Android 分为主线程和子线程
//    主线程就是APP一启动后，咱们Android framework层会启动一个线程，主线程（UI线程）
//    子线程 --new thread().start()
    /**
     * get 同步请求
     */
    fun get() {
        Thread(Runnable {
//    构造请求体
            val request: Request = Request.Builder().url("$baseUrl/api/v1/dashboard").build()
//    构造请求对象
            val call: Call = client.newCall(request)
//    发起同步请求execute--同步执行，100ms，1000ms
            val response: Response = call.execute()
            val body: String? = response.body?.string()
            Log.e("OKHTTP", "get response:${body}")
        }).start()
    }

    /**
     * get 异步请求
     */
    fun getAsync() {
        Thread(Runnable {
//    构造请求体
            val request: Request = Request.Builder().url("$baseUrl/api/v1/dashboard").build()
//    构造请求对象
            val call: Call = client.newCall(request)
//    发起异步请求enqueue--异步执行，100ms，1000ms
            call.enqueue(
                object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        Log.e(
                            "OKHTTP getAsync onFailure",
                            "getAsync response onFailure:${e.message}"
                        )
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val body: String? = response.body?.string()
                        Log.e("OKHTTP getAsync onResponse", "getAsync response onResponse:${body}")
                    }
                }
            )
        }).start()
    }

    /**
     * post 同步请求
     */
    fun post() {
        val body = FormBody.Builder()
            .add("name", "little black")
            .add("status", "unsold")
            .build()
        val request: Request =
            Request.Builder().url("$baseUrl/pet")
                .post(body)
                .build()
        val call: Call = client.newCall(request)
        Thread(Runnable {
            val response = call.execute()
            Log.e("OKHTTP post", "post response onResponse:${response.body?.string()}")
        }).start()
    }

    /**
     * post 异步请求【表单提交】
     */
    fun postAsync() {
        val body = FormBody.Builder()
            .add("name", "little black")
            .add("status", "unsold")
            .build()
        val request: Request =
            Request.Builder().url("$baseUrl/pet")
                .post(body)
                .build()
        val call: Call = client.newCall(request)
        Thread(Runnable {
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("OKHTTP postAsync", "postAsync response onFailure:${e.message}")
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.e(
                        "OKHTTP postAsync",
                        "postAsync response onResponse:${response.body?.string()}"
                    )
                }
            })
        }).start()
    }

    /**
     * post 异步请求【多表单文件上传】，在Android6.0及以后，读取外部存储卡的文件都是需要动态申请权限
     */
    fun postAsyncMultipart(context: Context) {
        val file = File(Environment.getExternalStorageDirectory(), "1.png")
        if (!file.exists()) {
            Toast.makeText(context, "文件不存在", Toast.LENGTH_SHORT).show()
            return
        }
        val body = MultipartBody.Builder()
            .addFormDataPart("key", "value")
            .addFormDataPart(
                "file",
                "file.png",
                RequestBody.create("application-octet-stream".toMediaType(), file)
            )
            .build()
        val request: Request =
            Request.Builder().url("接口是需要支持文件上传才可以使用的")
                .post(body)
                .build()
        val call: Call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(
                    "OKHTTP postAsyncMultipart",
                    "postAsyncMultipart response onFailure:${e.message}"
                )
            }

            override fun onResponse(call: Call, response: Response) {
                Log.e(
                    "OKHTTP postAsyncMultipart",
                    "postAsyncMultipart response onResponse:${response.body?.string()}"
                )
            }
        })
    }

    /**
     * 异步post请求【提交字符串】
     */
    fun postString() {
        val textPlain = "text/plain;charset=utf-8".toMediaType()
        val applicationJSON = "application/json;charset=uft-8".toMediaType()
        val jsonObject = JSONObject()
        jsonObject.put("key1", "value1")
        jsonObject.put("key2", 100)

        val textObj = "username:username;password:password"

        val body = RequestBody.create(textPlain, textObj)

        val request: Request =
            Request.Builder().url("$baseUrl/pet")
                .post(body)
                .build()
        val call: Call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("OKHTTP postString", "postString response onFailure:${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.e(
                    "OKHTTP postString",
                    "postString response onResponse:${response.body?.string()}"
                )
            }
        })

    }
}
