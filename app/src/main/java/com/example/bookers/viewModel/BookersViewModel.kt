package com.example.bookers.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookers.models.Book
import com.example.bookers.models.Repository
import com.example.bookers.models.gsonModels.Data
import com.example.bookers.models.gsonModels.Item
import java.util.logging.Handler

class BookersViewModel : ViewModel() {
    val repository = Repository()
    var data = MutableLiveData<List<Item>>().apply { value = listOf() }
    var dataFav = MutableLiveData<List<Item>>().apply { value = listOf() } //This will be got from Room db

    init {
        fetchData("volumes?q=argentina")
    }

    private fun fetchData(url: String){
        repository.fetchData(url)
        android.os.Handler().postDelayed({ //This will be with coroutines
            data.postValue(repository.dataInfo.value)
        }, 1000)
    }


    var actualFragment = MutableLiveData<String>().apply {
        this.value = "listFragment"
    }
    var selectedBook = MutableLiveData<Item>()

    fun setFragment(fragmentName: String) {
        actualFragment.postValue(fragmentName)
    }

    fun select(book: Item) {
        selectedBook.postValue(book)
    }
}