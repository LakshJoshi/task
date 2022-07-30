package com.practicaltask.data.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.moneyme2.data.enum.APIErrorCode
import com.moneyme2.data.enum.HTTPCode
import com.practicaltask.R
import com.practicaltask.data.entity.APIResponse
import com.practicaltask.data.entity.APIResult
import com.practicaltask.data.remote.APIManager
import com.practicaltask.util.helper.Helper
import org.json.JSONObject


class HomeRepository(val context: Context) {

    private val api = APIManager.invoke(context)


    suspend fun getHomeData(
        listener: (APIResult<String>) -> Unit
    ) {
        if (Helper.isNetworkAvailable(context)) {
            listener.invoke(APIResult.InProgress)


            val jsonObj = JSONObject()
            jsonObj.put("customer_id","")

            Log.d("params", Gson().toJson(jsonObj))
            try {
                val response = api.getHomeData(jsonObj)
                if (response.isSuccessful) {
                    Log.d("Response", Gson().toJson(response.body()))
                    response.body()?.asJsonObject?.let { jsonObject ->
                        val apiResponse: APIResponse = Gson().fromJson(jsonObject, object : TypeToken<APIResponse>() {}.type)
                        Log.d("APIRes", Gson().toJson(apiResponse))
                        when (response.code()) {
                            HTTPCode.SUCCESS.code, HTTPCode.SUCCESS_1.code -> {
                                listener.invoke(APIResult.Success("", apiResponse.message))
                                return
                            }
                            else -> {
                                listener(APIResult.Failure(APIErrorCode.INVALID_DATA, context.getString(R.string.error_msg)))
                                return
                            }
                        }
                    }.let {

                        listener(
                            APIResult.Failure(
                                APIErrorCode.NO_RESPONSE,
                                context.getString(R.string.error_msg)
                            )
                        )
                        return
                    }
                } else {
                    listener(
                        APIResult.Failure(
                            APIErrorCode.INVALID_DATA,
                            context.getString(R.string.error_msg)
                        )
                    )
                    return
                }
            } catch (e: Exception) {
                Log.e("error", e.message.toString())
            }

        } else {
            listener(
                APIResult.Failure(
                    APIErrorCode.NETWORK_ERROR,
                    context.getString(R.string.network_error_msg)
                )
            )
            return
        }
    }
}