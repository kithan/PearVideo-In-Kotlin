package com.kotlin.pearvideo.main

import com.kotlin.kunlun.vmovier_in_kotlin.IMvpView
import com.kotlin.kunlun.vmovier_in_kotlin.IPresenter
import com.kotlin.pearvideo.model.Category
import com.kotlin.pearvideo.model.PearNode


interface HomeContract {

    interface View : IMvpView {
        fun setCategoryTabs(tabCategory: List<Category>)
    }

    interface Presenter : IPresenter<View> {
        fun getCategory()
    }
}