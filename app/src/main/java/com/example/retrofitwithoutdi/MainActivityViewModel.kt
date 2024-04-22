package com.example.retrofitwithoutdi

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitwithoutdi.model.RecyclerData
import com.example.retrofitwithoutdi.model.ReposnseList
import com.example.retrofitwithoutdi.network.ApiResult
import com.example.retrofitwithoutdi.network.RetrofitInstance
import com.example.retrofitwithoutdi.network.RetrofitService
import com.example.retrofitwithoutdi.sqlDataBase.dao.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainActivityViewModel : ViewModel() {

     val recyclerLiveListData = MutableLiveData<ApiResult<ReposnseList>>() // Replace ResponseDataType with your actual data type
    fun makeApiCall() {
        recyclerLiveListData.value = ApiResult.Loading // Set loading state
        viewModelScope.launch {
            try {
                val retrofitInstance = RetrofitInstance.getRetroInstance().create(RetrofitService::class.java)
                val response = retrofitInstance.getDataFromApi("ny")

                recyclerLiveListData.value = ApiResult.Success(response)
            } catch (e: IOException) {
                recyclerLiveListData.value = ApiResult.Error(e)
            } catch (e: HttpException) {
                recyclerLiveListData.value = ApiResult.Error(e)
            }
        }
    }

    fun insertUserDataToDb(){
        CoroutineScope(Dispatchers.IO).launch{
            val newUser = User(username = "JohnDoe", email = "john@example.com", password = "123", mobileNumber = "2525252525", id = 25)
            AppClass.database.userDao().insertUser(newUser)
        }
    }

    private suspend fun getAllUserFromDb() : List<User>{
        return withContext(Dispatchers.IO){
            AppClass.database.userDao().getAllUsers()
        }
    }
    fun fetchData() {
        viewModelScope.launch {
            val userList = getAllUserFromDb()
            Log.e("getDataFromDb", userList.toString())
        }
    }

    fun updateUserData(user: User) {
        viewModelScope.launch {
            AppClass.database.userDao().updateUser(user)
        }
    }

    fun deleteUserById(id : Int){
        viewModelScope.launch {
            AppClass.database.userDao().deleteUserById(id)
        }
    }
}