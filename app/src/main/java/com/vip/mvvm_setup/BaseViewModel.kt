package com.vip.mvvm_setup

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rhombus.budgetpharma.Utils.SharedPreferenceUtils
import com.vip.mvvm_setup.data.api_manager.RepositoryApiDataManager
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BaseViewModel<N>(internal var application: Application) : AndroidViewModel(application) {
    val dataManager: RepositoryApiDataManager = RepositoryApiDataManager(application)
    val preferenceUtils: SharedPreferenceUtils = SharedPreferenceUtils(application)
    public val compositeDisposable: CompositeDisposable = CompositeDisposable()
    public val isLoading = MutableLiveData<Boolean>()
    public var mNavigator: WeakReference<N>? = null //WeakReference is used to  avoid memory leaks

    init { }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun ShowToast(message: String) {
        Toast.makeText(application, "" + message, Toast.LENGTH_SHORT).show()
    }

    infix fun setIsLoading(isLoad: Boolean) {
        isLoading.value = isLoad
    }

    val navigator: N? get() = mNavigator!!.get()

    fun setNavigator(navigator: N) {
        mNavigator = WeakReference(navigator)
    }

}