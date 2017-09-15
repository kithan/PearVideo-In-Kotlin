package com.kotlin.pearvideo.main

import com.kotlin.kunlun.vmovier_in_kotlin.IMvpView
import com.kotlin.kunlun.vmovier_in_kotlin.IPresenter

interface PaikeContract {

    interface View : IMvpView

    interface Presenter : IPresenter<View>
}