package com.example.bookers.models


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.bookers.models.gsonModels.Data
import com.example.bookers.models.gsonModels.Item
import com.example.bookers.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Repository for API and make operations with database TODO
 */
class Repository {

    private val apiInterface = ApiInterface.create()
    var dataInfo = MutableLiveData<List<Item>>()

    suspend fun fetchData(url: String) {
        val response = apiInterface.getData(url)
        if(response.isSuccessful) dataInfo.postValue(response.body()!!.items)
        else dataInfo.postValue(listOf())
    }


}
