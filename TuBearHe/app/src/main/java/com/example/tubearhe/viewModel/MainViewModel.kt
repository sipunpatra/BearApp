package com.example.tubearhe.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tubearhe.model.Bear
import com.example.tubearhe.retrofit.ApiService
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {

    var bearListResponse:List<Bear> by mutableStateOf(listOf())

    var errorMessage:String by mutableStateOf("")

    fun getBearList(){
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val bearList = apiService.getBears()
                bearListResponse = bearList
            }catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }
    }
}