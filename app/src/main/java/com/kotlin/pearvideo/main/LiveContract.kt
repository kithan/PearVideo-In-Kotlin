package com.kotlin.pearvideo.home

import com.kotlin.kunlun.vmovier_in_kotlin.IMvpView
import com.kotlin.kunlun.vmovier_in_kotlin.IPresenter

interface LiveContract {

    interface View : IMvpView

    interface Presenter : IPresenter<View>
}