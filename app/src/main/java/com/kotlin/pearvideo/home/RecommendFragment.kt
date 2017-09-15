package com.kotlin.pearvideo.home

import com.kotlin.kunlun.vmovier_in_kotlin.BaseFragment
import com.kotlin.pearvideo.R

class RecommendFragment : BaseFragment<LivePresenter>(), LiveContract.View {
    override fun initPresenter(): LivePresenter {
       return LivePresenter()
    }

    override val contentView: Int
        get() = R.layout.fragment_home_recommend

    override fun initViews() {
    }
}