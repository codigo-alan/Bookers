package com.example.bookers.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookers.models.Repository
import com.example.bookers.models.gsonModels.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookersViewModel : ViewModel() {
    val repository = Repository()
    var search = MutableLiveData<String>()
    var data = MutableLiveData<List<Item>>().apply { value = listOf() }
    var dataFav = MutableLiveData<List<Item>>().apply { value = listOf() } //This will be got from Room db

    init {
        search.value = "argentina"
        fetchData("volumes?q=" + search.value!!)
    }

    fun fetchData(url: String){

        viewModelScope.launch {
            val dates = withContext(Dispatchers.IO) { repository.fetchData(url) }
            //data.postValue(dates!!.items) //TODO view null case
            data.postValue(repository.dataInfo.value)
        }


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