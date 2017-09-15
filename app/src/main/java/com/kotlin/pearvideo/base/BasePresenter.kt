package com.kotlin.kunlun.vmovier_in_kotlin

import com.trello.rxlifecycle2.LifecycleProvider


//import com.example.kunlun.data.HttpResult
//import com.example.kunlun.data.VmovierApi
//import com.example.kunlun.db.DbHelper
//import com.example.kunlun.db.VideoInfoDao


abstract class BasePresenter<T : IMvpView> : IPresenter<T> {


    companion object {
//        @JvmStatic
//        val videoInfoDao: VideoInfoDao = DbHelper.instance.daoSession!!.videoInfoDao

    }

    protected   var mvpView: T?=null
    protected  lateinit  var provider:LifecycleProvider<*>
    //


    override fun attachView(mView: IMvpView) {
        this.mvpView = mView as T
        if(mvpView is LifecycleProvider<*>){
            provider= mvpView as LifecycleProvider<*>
        }
    }


    override fun detachView() {
        mvpView = null
    }


}

