package com.repose.noted.model

import androidx.lifecycle.*
import com.repose.noted.data.Starred
import com.repose.noted.data.StarredDao
import kotlinx.coroutines.launch

class RoomViewModel(private val starredDao: StarredDao) : ViewModel() {

//    val allPaths: LiveData<Starred> = starredDao.getPaths().asLiveData()

//    val allPdfNames : LiveData<Starred> = starredDao.getPdfNames().asLiveData()

    val allItems : LiveData<List<Starred>> = starredDao.getItems().asLiveData()


    private fun insertItem(item: Starred) {
        viewModelScope.launch {
            starredDao.insert(item)
        }
    }

    fun isEntryValid(path: String, pdfName: String): Boolean {
        if (path.isBlank() || pdfName.isBlank() ) {
            return false
        }
        return true
    }


    private fun getNewItemEntry(path: String, name: String): Starred {
        return Starred(
            path = path,
            dbname = name,
        )
    }

    fun addNewItem(path: String, pdfName: String) {
        val newItem = getNewItemEntry(path, pdfName)
        insertItem(newItem)
    }

    fun deleteItem(item: Starred) {
        viewModelScope.launch {
            starredDao.delete(item)
        }
    }


}

class RoomViewModelFactory(private val starredDao: StarredDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RoomViewModel(starredDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
