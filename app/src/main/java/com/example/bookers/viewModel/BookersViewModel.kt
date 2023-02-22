package com.example.bookers.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookers.database.BookEntity
import com.example.bookers.models.Caster
import com.example.bookers.models.Repository
import com.example.bookers.models.gsonModels.Item
import com.example.bookers.models.gsonModels.VolumeInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookersViewModel : ViewModel() {
    val repository = Repository()
    //private val caster = Caster()
    var search = MutableLiveData<String>()
    var data = MutableLiveData<List<Item>>().apply { value = listOf() }
    var dataFav = MutableLiveData<List<Item>>().apply { value = listOf() } //This will be got from Room db

    init {
        search.value = "argentina"
        fetchDataFav()
        fetchDataPrev("volumes?q=" + search.value!!)
        //fetchData("volumes?q=" + search.value!!)
    }

    fun fetchDataPrev(url: String) {
        viewModelScope.launch {
            data.postValue(fetchData(url))
        }
    }

    suspend fun fetchData(url: String) : List<Item>{

        val newData = viewModelScope.async {
            repository.fetchData(url)
            //data.postValue(repository.dataInfo.value)
        }

        return newData.await()
    }

    fun fetchDataFav(){
        repository.getBooksFromDb()
        val bookList: List<Item>? = repository.dataInfoFav.value?.map(::entityToItem)
        Log.d("favorites", "$bookList")
        if (bookList == null) dataFav.postValue(listOf())
        else dataFav.postValue(bookList)

    }

    private fun entityToItem(bookEntity: BookEntity): Item {
        return Item(id = bookEntity.id, volumeInfo = VolumeInfo(description = bookEntity.description, title = bookEntity.title))

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
    fun setSearchString(newSearch: String) {
        search.postValue(newSearch)
    }
}