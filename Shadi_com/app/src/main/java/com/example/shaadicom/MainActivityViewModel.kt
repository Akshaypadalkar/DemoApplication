package com.example.shaadicom

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.example.shaadicom.database.AppDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivityViewModel(private val dispatcher: CoroutineDispatcher,
                            private val repo: Repository)
    : ViewModel(), LifecycleObserver {
     val  userInfoData = MutableLiveData<UserModel>()
    var isLoading = ObservableField<Boolean>(true)

    @InternalCoroutinesApi
fun getUserData(){
    viewModelScope.launch(dispatcher) {
        try {
            repo.fetchUsers().collect { res ->
                val userinfo=res.body()
                isLoading.set(false)
                userinfo?.let {
                    userInfoData.value=it
                }
                Log.e("inside res:", res.body().toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()

        }
    }
}
    fun  insertAllData(db:AppDatabase){
        viewModelScope.launch(dispatcher) {
//            db.userdeatislDao().insertUser()
        }
    }

}