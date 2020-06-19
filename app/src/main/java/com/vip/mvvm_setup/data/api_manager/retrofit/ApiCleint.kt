package com.vip.mvvm_setup.data.api_manager.retrofit


import com.vip.mvvm_setup.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory



import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiCleint {


    private var retrofit_O: Retrofit? = null

        private val stethoClient: OkHttpClient
            get() = OkHttpClient.Builder()
                    .addNetworkInterceptor(StethoInterceptor())
                    .build()


        fun getClientServerApi(): Retrofit? {

            if (retrofit_O == null) {
                retrofit_O = Retrofit.Builder()
                        .baseUrl(BuildConfig.BASEURL_STAGING)
                        //.client(okClient())
                        .client(stethoClient)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit_O
        }


        private fun okClient(): OkHttpClient {
            return OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .build()
        }


        private fun client(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return OkHttpClient.Builder().addInterceptor(interceptor).build()
        }

        private var okHttpClient: OkHttpClient? = null
        private fun initOkHttp() {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient().newBuilder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)

            okHttpClient = httpClient.build()
        }


}
