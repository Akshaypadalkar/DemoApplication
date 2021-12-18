package com.example.shaadicom

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class Repository(private  val apiServiceAPI: APIService) {

    suspend fun fetchUsers() : Flow<Response<UserModel>> {
        return  flow {
            val employeeInfo = apiServiceAPI.fetchUserData()
            emit(employeeInfo)
        }
    }
}