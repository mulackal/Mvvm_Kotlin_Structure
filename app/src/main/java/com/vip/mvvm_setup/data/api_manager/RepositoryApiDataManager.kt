package com.vip.mvvm_setup.data.api_manager


import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.rhombus.budgetpharma.AppDatas.ApiManager.retrofit.ApiInterface
import com.vip.mvvm_setup.R
import com.vip.mvvm_setup.data.api_manager.retrofit.ApiParams
import com.vip.mvvm_setup.data.api_manager.retrofit.ApiResource
import com.vip.mvvm_setup.ui.activity.home.HomeCallListener
import com.vip.mvvm_setup.ui.activity.home.HomeCategoryRespond
import com.vip.mvvm_setup.ui.activity.login.LoginResponds
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit


class RepositoryApiDataManager(internal var mContext: Context) {


    private val mRetrofit: Retrofit? = com.vip.mvvm_setup.data.api_manager.retrofit.ApiCleint.getClientServerApi()

    var apiInterface = mRetrofit!!.create(ApiInterface::class.java)!!

    init { }


   fun LoginRequest(
       data: ApiParams,
       apiRespondsData: MutableLiveData<ApiResource<LoginResponds>>
   ): MutableLiveData<ApiResource<LoginResponds>>  {

       if (apiInterface == null) {
            apiInterface = mRetrofit!!.create(ApiInterface::class.java)
        }

        val ResponseObservable = apiInterface.login(data)
        ResponseObservable.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<LoginResponds> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(response: LoginResponds) {
                    Log.e("LoginCall", "- ${response.message}")
                     if(response.status!!)
                         apiRespondsData.value = ApiResource.success(response)
                    else
                         apiRespondsData.value = ApiResource.error(response.message,null)
                }

                override fun onError(e: Throwable) {
                    Log.e("LoginCall", "- ${e.message}")
                    apiRespondsData.value = ApiResource.error(mContext.resources.getString(R.string.apierror),null)
                    e.printStackTrace()
                }

                override fun onComplete() {}
            })
       return apiRespondsData
    }


    fun HomeDataList(
        data: ApiParams,
        responseListener: HomeCallListener
    ) {
        if (apiInterface == null) {
            apiInterface = mRetrofit!!.create(ApiInterface::class.java)
        }

        val ResponseObservable = apiInterface.subCategoryProducts(data)
        ResponseObservable.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<HomeCategoryRespond> {
                override fun onSubscribe(d: Disposable) {
                    //compositeDisposable.add(d)
                }

                override fun onNext(callListRespond: HomeCategoryRespond) {
                    responseListener.onSuccess(callListRespond.data!!)
                }

                override fun onError(e: Throwable) {
                    responseListener.onFailure("No data found!")
                }

                override fun onComplete() {
                    Log.e("onComplete", "onComplete")
                }
            })
    }

}