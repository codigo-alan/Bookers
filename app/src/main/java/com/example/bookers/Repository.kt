package com.example.bookers

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.bookers.models.gsonModels.Data
import com.example.bookers.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private val apiInterface = ApiInterface.create()
    var dataInfo = MutableLiveData<Data?>()

    fun fetchData(): MutableLiveData<Data?> {
        val call = apiInterface.getData()
        call.enqueue(object: Callback<Data> {
            override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.e("ERROR", t.message.toString())
                dataInfo.postValue(null)
            }
            override fun onResponse(call: Call<Data?>, response: Response<Data?>) {
                if (response != null && response.isSuccessful) {
                    dataInfo.value = response.body()
                }
            }
        })
        return dataInfo
    }

}