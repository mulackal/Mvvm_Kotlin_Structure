package com.vip.mvvm_setup.ui.activity.home

interface HomeCallListener {
    fun onSuccess(data: List<HomeProductwiseData>)
    fun onFailure(message: String?)
}