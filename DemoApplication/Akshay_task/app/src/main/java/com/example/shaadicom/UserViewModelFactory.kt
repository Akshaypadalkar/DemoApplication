package com.example.shaadicom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers

class UserViewModelFactory : ViewModelProvider.Factory {
    lateinit var employeeRepository: Repository
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        employeeRepository = Network().provideEmployeeRepository()
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(Dispatchers.Main, employeeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}