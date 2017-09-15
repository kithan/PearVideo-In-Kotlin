package com.kotlin.pearvideo.home

import com.kotlin.fivehundred.a500px_in_kotlin.data.Repository
import com.kotlin.kunlun.vmovier_in_kotlin.BasePresenter
import com.kotlin.pearvideo.data.SimpleDisposableObserver
import com.kotlin.pearvideo.model.PearNode


class HotPresenter : BasePresenter<HotContract.View>(), HotContract.Presenter{
    override fun requestHotList(start: Int) {
        Repository.instance.getHotList(start,provider,object : SimpleDisposableObserver<List<PearNode>>(){
            override fun onNext(t: List<PearNode>) {
                mvpView!!.updateHotData(t)
            }
        })
    }
}