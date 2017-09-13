package com.kotlin.kunlun.vmovier_in_kotlin

/**
 * Created by hpb on 2017/8/17.
 */

interface IPresenter<T : IMvpView> {
    fun attachView(mvpView: IMvpView)
    fun detachView()
}
