package com.rhombus.budgetpharma.AppDatas.ApiManager.retrofit



import com.vip.mvvm_setup.ui.activity.home.HomeCategoryRespond
import com.vip.mvvm_setup.ui.activity.login.LoginResponds
import com.vip.mvvm_setup.data.api_manager.retrofit.ApiParams

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {


     @POST("dmanlogin")
       fun login(@Body content: ApiParams): Observable<LoginResponds>

    @FormUrlEncoded
    @POST("dmanlogin")
    fun loginCall(    @Field("username") user: String,
                      @Field("password") pass: String): Observable<LoginResponds>

    @POST("categorywiseproducts")
    fun subCategoryProducts(@Body data: ApiParams): Observable<HomeCategoryRespond>


}
