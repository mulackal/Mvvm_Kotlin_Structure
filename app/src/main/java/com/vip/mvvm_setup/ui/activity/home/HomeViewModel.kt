package com.vip.mvvm_setup.ui.activity.home

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import com.google.gson.Gson
import com.vip.mvvm_setup.BaseViewModel
import com.vip.mvvm_setup.data.SharedValues
import com.vip.mvvm_setup.data.api_manager.retrofit.ApiParams
import com.vip.mvvm_setup.data.api_manager.retrofit.DataApi
import com.vip.mvvm_setup.data.api_manager.retrofit.Header
import com.vip.mvvm_setup.utils.NetworkServiceManager

class HomeViewModel(var context: Application) : BaseViewModel<HomeListenerView?>(context) {
    var mDataList: ObservableList<HomeProductwiseData> = ObservableArrayList()
    var fromDate = ObservableField("")

    init {
        loadDetails()
    }

    fun loadDetails() {
        if (NetworkServiceManager.getInstance(context).isNetworkAvailable) {

            setIsLoading(true)

            val header = Header()
            header.firmcode =  preferenceUtils!!.getStringValue(SharedValues.FIRMCODE, "04")
            header.subcatcode = "P00048"  // P00049 , P00041 , P00053  , P00033

            val datum = DataApi()
            datum.header = header

            val otpclass = ApiParams()
            otpclass.data = listOf(datum)

            val gson = Gson()
            var json = gson.toJson(otpclass)

            Log.e("CallOrder","- $json")

            dataManager?.HomeDataList(
                otpclass,
                 object : HomeCallListener {
                     override fun onSuccess(data: List<HomeProductwiseData>) {
                         setDataList(data)
                     }
                     override fun onFailure(message: String?) {
                        // Send event to UI to show thw error
                        ShowErrorSnak(message)
                    }
                })
        } else {
            ShowErrorSnak("No network")
        }
    }

    fun setDataList(data: List<HomeProductwiseData>?) {
        setIsLoading(false)
        if (data != null && data.isNotEmpty()) {
            mDataList.clear()
            mDataList.addAll(data)
        } else navigator!!.ShowWarningMessage("No data available")
    }

    fun ShowErrorSnak(message: String?) {
        Log.e("error", "" + message)
        setIsLoading(false)
        navigator!!.ShowWarningMessage(message)
    }

    fun deleteItem(data: String?, position: Int) {
        navigator!!.deleteItem(data, position)
    }

    fun OnLoadingData() {
        loadDetails()
    }



}