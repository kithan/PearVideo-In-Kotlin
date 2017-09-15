package com.kotlin.pearvideo.main

import com.kotlin.fivehundred.a500px_in_kotlin.data.Repository
import com.kotlin.kunlun.vmovier_in_kotlin.BasePresenter
import com.kotlin.pearvideo.data.SimpleDisposableObserver
import com.kotlin.pearvideo.model.Category
import com.kotlin.pearvideo.model.PearNode


class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter {
    override fun getCategory(){
        Repository.instance.getCategorys(provider, object : SimpleDisposableObserver<List<Category>>() {
            override fun onNext(t: List<Category>) {
                mvpView!!.setCategoryTabs(t)
            }
        })
    }
}