package com.vip.mvvm_setup.ui.activity.home

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.vip.mvvm_setup.BaseActivity
import com.vip.mvvm_setup.R
import com.vip.mvvm_setup.databinding.HomeDataBinding


class HomePage : BaseActivity(), HomeListenerView {
    var myDataBinding: HomeDataBinding? = null
    var mAdapter: HomeAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        myDataBinding!!.homeviewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        myDataBinding!!.homeviewModel!!.setNavigator(this)

        setupRecyclerView()
        ObservedLoader()
    }

    private fun ObservedLoader() {
        myDataBinding!!.homeviewModel!!.isLoading.observe(
            this,
            Observer { isLoading ->
                if (isLoading != null) {
                    if (isLoading) showLoadingDialog(this@HomePage) else hideaLoadingDialog()
                }
            })
    }

    private fun setupRecyclerView() {
        mAdapter = HomeAdapter(this)
        myDataBinding!!.dataList.layoutManager = LinearLayoutManager(this)
        myDataBinding!!.dataList.adapter = mAdapter
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        myDataBinding!!.unbind()
        super.onDestroy()
    }

    override fun ShowWarningMessage(msg: String?) {}
    override fun ItemClick(id: String?) {}
    override fun deleteItem(data: String?, pos: Int) {}
}