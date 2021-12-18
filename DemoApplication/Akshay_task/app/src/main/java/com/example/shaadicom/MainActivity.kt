package com.example.shaadicom

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.shaadicom.adapter.RecyclerViewOnItemClickListener
import com.example.shaadicom.adapter.UserAdapter
import com.example.shaadicom.adapter.UserMatchCardViewModel
import com.example.shaadicom.database.AppDatabase
import com.example.shaadicom.database.UserDetailsEntity
import com.example.shaadicom.databinding.ActivityMainBinding
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.InternalCoroutinesApi
import android.net.ConnectivityManager

import android.net.NetworkInfo
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    lateinit var userViewModel:MainActivityViewModel
    lateinit var binding: ActivityMainBinding

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        initViewModel()
        getUserData()
    }



    private  fun initViewModel(){
        try {
            val employeeViewModelFactory = UserViewModelFactory()
             userViewModel = ViewModelProvider(this, employeeViewModelFactory).get(MainActivityViewModel::class.java)
        }catch(e:Exception){
            e.printStackTrace()
        }
    }

    @InternalCoroutinesApi
    fun getUserData (){
        if(CheckInternetConnection(this)) {
            if (AppDatabase.getInstance().userdeatislDao().getUsers().isEmpty()) {
                userViewModel.getUserData()
                userViewModel.userInfoData.observe(this, Observer {
                    if (it != null && it.results.isNotEmpty()) {
                        val result = it.results
                        val mlist = mutableListOf<UserDetailsEntity>()
                        var i = 1
                        result.forEach {
                            mlist.add(
                                UserDetailsEntity(
                                    i,
                                    "${it.name.first} ${it.name.last}",
                                    "${it.gender} / ${it.dob.age}",
                                    "${it.location.street.name} ${it.location.city}",
                                    it.picture.large
                                )
                            )
                            i++
                        }
                        AppDatabase.getInstance().userdeatislDao().insertAll(mlist)
                        getDataFromDb()
                    }
                })

            } else
                getDataFromDb()
        }else{
            Toast.makeText(this,"Please check internet connection",Toast.LENGTH_SHORT).show()
            if (AppDatabase.getInstance().userdeatislDao().getUsers().isNotEmpty())
                getDataFromDb()
        }


    }
    fun getDataFromDb(){
        val list= AppDatabase.getInstance().userdeatislDao().getUsers()
                val useradapter=UserAdapter(list)
                binding.userRecycleView.adapter=useradapter
        useradapter.itemClickListener=object: RecyclerViewOnItemClickListener {
            @SuppressLint("CheckResult")
            override fun onItemClick(id: Int, userMatchCardViewModel: UserMatchCardViewModel,position:Int) {
            if (id==R.id.btn_accept) {
//                view.text = getString(R.string.member_accepted)
                userMatchCardViewModel.accept.set(getString(R.string.member_accepted))
                userMatchCardViewModel.matchcard.accept=(getString(R.string.member_accepted))
                userMatchCardViewModel.decline.set(getString(R.string.decline))
                userMatchCardViewModel.matchcard.decline=(getString(R.string.decline))
            }

                if(id==R.id.btn_decline) {
//                    view.text = "Declined"
                    userMatchCardViewModel.accept.set(getString(R.string.accept))
                    userMatchCardViewModel.matchcard.accept=(getString(R.string.accept))
                    userMatchCardViewModel.decline.set(getString(R.string.declined))
                    userMatchCardViewModel.matchcard.decline=(getString(R.string.declined))
                }
                userMatchCardViewModel.notifyChange()

                AppDatabase.getInstance().userdeatislDao().updateUser(userMatchCardViewModel.matchcard)
            }
        }
    }

    fun CheckInternetConnection(context: Context): Boolean {
        var status: Boolean = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        if (activeNetwork != null) {
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                status = true
                return status
            } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                status = true
                return status
            }
        } else {
            status = false
            return status
        }
        return status
    }
}