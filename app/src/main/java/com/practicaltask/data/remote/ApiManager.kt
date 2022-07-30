package com.practicaltask.data.remote

import android.content.Context
import android.util.Log
import com.google.gson.JsonElement
import com.practicaltask.util.constant.AppURL
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface APIManager {

    companion object {
        private const val API_KEY = ""

        private fun getOkHttpClient(context: Context): OkHttpClient {

            var request: Request?
            val httpClient = OkHttpClient.Builder()
                .connectTimeout(90, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)

            httpClient.addInterceptor { chain ->
                val original = chain.request()

                val hasToken = true
                request = when {
                    hasToken -> {
                        original.newBuilder()
                            .header("ApiKey", API_KEY)
                            .header("Accept", "application/json")
                            .method(original.method, original.body)
                            .build()
                    }
                    else -> {
                        original.newBuilder()
                            .header("ApiKey", API_KEY)
                            .header("Accept", "application/json")
                            .method(original.method, original.body)
                            .build()
                    }
                }
                Log.d("Token","ElseToken")
                val response = chain.proceed(request!!)
                val responseBody = response.body
                val contentType = responseBody?.contentType()
                val content = responseBody?.string()

                response.newBuilder().body(ResponseBody.create(contentType, content!!)).build()
            }

            return httpClient.build()
        }

        operator fun invoke(context: Context): APIManager {
            val retrofit = Retrofit.Builder()
                .baseUrl(AppURL.API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient(context))
                .build()
            return retrofit.create(APIManager::class.java)
        }
    }


    @POST(AppURL.API.HOME_API)
    suspend fun getHomeData(@Body params: JSONObject): Response<JsonElement>

}