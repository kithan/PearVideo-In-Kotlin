package com.kotlin.pearvideo.home

import com.kotlin.kunlun.vmovier_in_kotlin.BaseFragment
import com.kotlin.pearvideo.R

class SubscribeFragment : BaseFragment<SubscribePresenter>(), SubscribeContract.View {
    override fun initPresenter(): SubscribePresenter {
       return SubscribePresenter()
    }

    override val contentView: Int
        get() = R.layout.fragment_main_subscribe

    override fun initViews() {
    }
}