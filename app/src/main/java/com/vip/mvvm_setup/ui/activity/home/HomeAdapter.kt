package com.vip.mvvm_setup.ui.activity.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vip.mvvm_setup.R
import com.vip.mvvm_setup.databinding.ItemHomeDataBinding
import com.vip.mvvm_setup.ui.activity.home.HomeAdapter.MyViewHolder
import java.util.*

class HomeAdapter(var mcontext: Context) :
    RecyclerView.Adapter<MyViewHolder>() {

    //   HomeListener homeListener;

    var nDataList: List<HomeProductwiseData> = ArrayList()

    fun setHomeDataList(DataList: List<HomeProductwiseData>) {
        nDataList = DataList
        notifyDataSetChanged()
    }

    inner class MyViewHolder(var itemViewBind: ItemHomeDataBinding) :
        RecyclerView.ViewHolder(itemViewBind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemHomeDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.home_items,
            parent,
            false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemViewBind.itemHomeData = nDataList!![position]
        holder.itemView.setOnClickListener {
            //  homeListener.loanClick(nDataList.get(position).getIntAutoNo(),nDataList.get(position).getStatus());
        }
    }

    override fun getItemCount(): Int {
        return if (nDataList != null) nDataList!!.size else 0
    }

}
