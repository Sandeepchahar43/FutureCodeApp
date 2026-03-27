package com.example.futurecodeapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository):ViewModel() {

    private var userData = MutableLiveData<List<User>>()
     val _userData:LiveData<List<User>> = userData

     fun getAllData(){
         viewModelScope.launch(Dispatchers.IO) {
             var result = userRepository.getUserData()
              userData.postValue(result)

         }


     }
}