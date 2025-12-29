package com.example.bottomnavigationapplication.http

import android.util.Log
import com.example.bottomnavigationapplication.BuildConfig
import com.example.bottomnavigationapplication.model.Course
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit
private const val baseUrl = BuildConfig.BASE_URL

object HiRetrofit {
    private val client: OkHttpClient = OkHttpClient.Builder() // builder构造者设计模式
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(LoggingInterceptor())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create()) // 数据转换适配器
        .build()

//      public <T> T create(final Class<T> service) {

    fun <T> create(clazz: Class<T>): T {
        Log.e("HiRetrofit",baseUrl)
        return retrofit.create(clazz)
    }
}

interface ApiService {
    @GET("pet")
    fun queryPet(@Query("petId", encoded = true) petId: String): Call<PetResponse>

    @GET("course/study")
    fun getStudy():Call<List<Course>>
}

data class CommonResponse(
    val data: List<Course>,
    val message: String,
    val success: Boolean
)
