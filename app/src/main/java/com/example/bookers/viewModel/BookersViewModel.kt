package com.example.bookers.viewModel

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookers.models.Book

class BookersViewModel : ViewModel() {
    var bookList = MutableLiveData<MutableList<Book>>().apply {
        this.value = mutableListOf<Book>(
            Book(1, "Book uno", "Description uno", "https://3.bp.blogspot.com/-2j0WLiDI6v8/WENaCfPuYnI/AAAAAAAHdHY/G_19kwoqRTAJV0yjZgNBrwGpKBZOyLPPQCLcB/s1600/harry-potter-free-printable-invitations-049.jpg"),
            Book(2, "Book dos", "Description dos", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTY-QeE858EkhRBahjIyVRpru3DWdIFvw7mKSOYbjEcOcQCLaQ3WP3R3_WCJTDWoVt0lOA&usqp=CAU"),
            Book(3, "Book tres", "Description tres", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRncXv_74VYtMWNvj56uzGI_fepKZoeV-hMAjgfNY5wKmopguC0ELz-qJjYCwNO_7BPQ2I&usqp=CAU"),
            Book(4, "Book uno", "Description uno", "https://3.bp.blogspot.com/-2j0WLiDI6v8/WENaCfPuYnI/AAAAAAAHdHY/G_19kwoqRTAJV0yjZgNBrwGpKBZOyLPPQCLcB/s1600/harry-potter-free-printable-invitations-049.jpg"),
            Book(5, "Book dos", "Description dos", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTY-QeE858EkhRBahjIyVRpru3DWdIFvw7mKSOYbjEcOcQCLaQ3WP3R3_WCJTDWoVt0lOA&usqp=CAU"),
            Book(6, "Book tres", "Description tres", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRncXv_74VYtMWNvj56uzGI_fepKZoeV-hMAjgfNY5wKmopguC0ELz-qJjYCwNO_7BPQ2I&usqp=CAU")
        )
    }
    var bookListFavourites = MutableLiveData<MutableList<Book>>().apply {
        this.value = mutableListOf<Book>(
            Book(1, "Book uno favourtite", "Description uno favourite", "https://3.bp.blogspot.com/-2j0WLiDI6v8/WENaCfPuYnI/AAAAAAAHdHY/G_19kwoqRTAJV0yjZgNBrwGpKBZOyLPPQCLcB/s1600/harry-potter-free-printable-invitations-049.jpg"),
            Book(2, "Book dos favourtite", "Description dos favourite", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTY-QeE858EkhRBahjIyVRpru3DWdIFvw7mKSOYbjEcOcQCLaQ3WP3R3_WCJTDWoVt0lOA&usqp=CAU"),
            Book(3, "Book tres favourtite", "Description tres favourite", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRncXv_74VYtMWNvj56uzGI_fepKZoeV-hMAjgfNY5wKmopguC0ELz-qJjYCwNO_7BPQ2I&usqp=CAU"),
            Book(4, "Book uno favourtite", "Description uno favourite", "https://3.bp.blogspot.com/-2j0WLiDI6v8/WENaCfPuYnI/AAAAAAAHdHY/G_19kwoqRTAJV0yjZgNBrwGpKBZOyLPPQCLcB/s1600/harry-potter-free-printable-invitations-049.jpg"),
            Book(5, "Book dos favourtite", "Description dos favourite", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTY-QeE858EkhRBahjIyVRpru3DWdIFvw7mKSOYbjEcOcQCLaQ3WP3R3_WCJTDWoVt0lOA&usqp=CAU"),
            Book(6, "Book tres favourtite", "Description tres favourite", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRncXv_74VYtMWNvj56uzGI_fepKZoeV-hMAjgfNY5wKmopguC0ELz-qJjYCwNO_7BPQ2I&usqp=CAU")
        )
    }

    var actualFragment = MutableLiveData<String>().apply {
        this.value = "listFragment"
    }
    var selectedBook = MutableLiveData<Book>()

    fun setFragment(fragmentName: String) { //TODO ??
        actualFragment.postValue(fragmentName)
    }

    fun select(book: Book) {
        selectedBook.postValue(book)
    }
}