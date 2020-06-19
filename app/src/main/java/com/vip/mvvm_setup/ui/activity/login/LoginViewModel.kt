package com.vip.mvvm_setup.ui.activity.login

import android.app.Application
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.vip.mvvm_setup.BaseViewModel
import com.vip.mvvm_setup.R
import com.vip.mvvm_setup.data.SharedValues
import com.vip.mvvm_setup.data.api_manager.retrofit.ApiParams
import com.vip.mvvm_setup.data.api_manager.retrofit.ApiResource
import com.vip.mvvm_setup.data.api_manager.retrofit.DataApi
import com.vip.mvvm_setup.data.api_manager.retrofit.Header
import com.vip.mvvm_setup.utils.NetworkServiceManager
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class LoginViewModel(activity: Application) : BaseViewModel<Any?>(activity) {

    var usernmae = ObservableField("")
    var password = ObservableField("")
    var apiRespondsData = MutableLiveData<ApiResource<LoginResponds>>()
    var context: Context = activity


    fun geLoginResult(): LiveData<ApiResource<LoginResponds>> {
        return apiRespondsData
    }

    fun login() {
        if (usernmae.get()!!.trim { it <= ' ' }.isNotEmpty() && password.get()!!.trim { it <= ' ' }.isNotEmpty()) {
            if (NetworkServiceManager.getInstance(context).isNetworkAvailable) {
                if (null != dataManager) {

                    setIsLoading(true)

                    val header = Header()
                    header.firmcode = preferenceUtils!!.getStringValue(SharedValues.FIRMCODE, "05")
                    header.mobile = usernmae.get()
                    header.password = password.get()

                    val datum = DataApi()
                    datum.header = header

                    val dataclass = ApiParams()
                    dataclass.data = listOf(datum)


                    val gson = Gson()
                    var json = gson.toJson(dataclass)

                    Log.e("LoginCall", "- $json")

                   dataManager.LoginRequest(dataclass,apiRespondsData)

                }
            }else{
                ShowWarningMessage(context.resources.getString(R.string.no_network))
            }
        } else {
            ShowWarningMessage("Enter valid information")
        }
    }

    fun ShowErrorMessage(message: String) {
        setIsLoading(false)
        ShowToast(message)
    }

    private fun ShowWarningMessage(message: String) {
        ShowToast(message)
    }


}