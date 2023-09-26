package com.example.patronobservable

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class RecyclerViewModel: ViewModel() {


    val recyclerList = MutableLiveData<List<User>>()

    init {
        recyclerList.value = mutableListOf(getUser())
    }


    fun addItemToList() {
        recyclerList.value = recyclerList.value?.plus(getUser())
    }



    private fun getUser():User {
        return User(R.drawable.messi_campeon, "Lionel Messi", "Campeón del mundo - Argentina 2022")
    }

    fun deleteItemToList() {
        recyclerList.value = recyclerList.value?.dropLast(recyclerList.value?.toList()!!.size-(recyclerList.value?.toList()!!.size-1))
    }
}


data class User(
    val image: Int,
    val title: String,
    val description: String
)