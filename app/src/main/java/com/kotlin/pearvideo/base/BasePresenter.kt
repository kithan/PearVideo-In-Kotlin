package com.kotlin.kunlun.vmovier_in_kotlin


//import com.example.kunlun.data.HttpResult
//import com.example.kunlun.data.VmovierApi
//import com.example.kunlun.db.DbHelper
//import com.example.kunlun.db.VideoInfoDao


abstract class BasePresenter<T : IMvpView> : IPresenter<T> {


    companion object {
//        @JvmStatic
//        val videoInfoDao: VideoInfoDao = DbHelper.instance.daoSession!!.videoInfoDao

    }

    var mvpView: T?=null
    //


    override fun attachView(mView: IMvpView) {
        this.mvpView = mView as T
    }


    override fun detachView() {
        mvpView = null
    }


}

