package com.example.bottomnavigationapplication.http

import android.util.Log
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val time_start: Long = System.nanoTime()
        val request: Request = chain.request()
        val response: Response = chain.proceed(request)

        val buffer = okio.Buffer()
        request.body?.writeTo(buffer)
        val requestBodyStr: String = buffer.readUtf8()
        Log.e(
            "OKHTTP",
            String.format("Sending request %s with params %s", request.url, requestBodyStr)
        )

        val gson = Gson();
        val businessData: String = response.body?.string() ?: "response body null"
        val businessJsonData =  gson.fromJson<CommonResponse>(businessData,CommonResponse::class.java).data
        val mediaTYpe = response.body?.contentType()
        val newBody = ResponseBody.create(mediaTYpe,gson.toJson(businessJsonData) )
        val newResponse = response.newBuilder().body(newBody).build()

        val time_end: Long = System.nanoTime()
        Log.e(
            "OKHTTP",
            String.format(
                "Receive response for %s in %.1fms >>> %s",
                request.url,
                (time_end - time_start) / 1e6,
                businessData
            )
        )

        return newResponse
    }
}