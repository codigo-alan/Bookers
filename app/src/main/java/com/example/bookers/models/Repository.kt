package com.example.bookers.models


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bookers.database.BookApplication
import com.example.bookers.database.BookEntity
import com.example.bookers.models.gsonModels.Data
import com.example.bookers.models.gsonModels.Item
import com.example.bookers.retrofit.ApiInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Repository for API and make operations with database
 */
class Repository {

    private val apiInterface = ApiInterface.create()
    var dataInfo = MutableLiveData<List<Item>>()
    var dataInfoFav = MutableLiveData<List<BookEntity>>()

    suspend fun fetchData(url: String) {
        val response = apiInterface.getData(url)
        if(response.isSuccessful) dataInfo.postValue(response.body()!!.items)
        else dataInfo.postValue(listOf())
    }

    fun insertBookToDb(bookEntity: BookEntity){
        CoroutineScope(Dispatchers.IO).launch {
            BookApplication.database.bookDao().addBook(bookEntity)
            withContext(Dispatchers.Main) {}
        }
    }

    fun deleteBookFromDb(bookEntity: BookEntity){
        CoroutineScope(Dispatchers.IO).launch {
            BookApplication.database.bookDao().deleteBook(bookEntity)
            withContext(Dispatchers.Main) {}
        }
    }

    fun getBooksFromDb(){
        CoroutineScope(Dispatchers.IO).launch {
            val booksFav = BookApplication.database.bookDao().getFavoritesBooks()
            withContext(Dispatchers.Main) {
                dataInfoFav.postValue(booksFav)
                println(booksFav)
            }
        }
    }


}
