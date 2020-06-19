package com.vip.mvvm_setup.ui.callback;


import com.vip.mvvm_setup.data.api_manager.retrofit.ApiResource;


public interface ApiCallListener {

    void onSuccess(ApiResource responds);
    void onFailure(String message);
}
