package com.kotlin.pearvideo.home

import com.kotlin.kunlun.vmovier_in_kotlin.IMvpView
import com.kotlin.kunlun.vmovier_in_kotlin.IPresenter
import com.kotlin.pearvideo.model.PearNode

interface HotContract {

    interface View : IMvpView{
        fun updateHotData(list:List<PearNode>)
    }

    interface Presenter : IPresenter<View>{
        fun requestHotList(start: Int)
    }
}