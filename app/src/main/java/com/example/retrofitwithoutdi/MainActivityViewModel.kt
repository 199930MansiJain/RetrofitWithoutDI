package com.example.retrofitwithoutdi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitwithoutdi.model.ReposnseList
import com.example.retrofitwithoutdi.network.RetrofitInstance
import com.example.retrofitwithoutdi.network.RetrofitService
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    var recyclerLiveListData : MutableLiveData<ReposnseList> = MutableLiveData()

    fun getRecyclerListObserver() : MutableLiveData<ReposnseList>{
        return recyclerLiveListData
    }

    fun makeApiCall(){
        viewModelScope.launch {
            val retrofitInstance =  RetrofitInstance.getRetroInstance().create(RetrofitService::class.java)
            val response = retrofitInstance.getDataFromApi("ny")
            recyclerLiveListData.postValue(response)
        }
    }
}