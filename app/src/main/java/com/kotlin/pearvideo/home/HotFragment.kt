package com.kotlin.pearvideo.home

import com.kotlin.kunlun.vmovier_in_kotlin.BaseFragment
import com.kotlin.pearvideo.R
import com.kotlin.pearvideo.model.PearNode


class HotFragment : BaseFragment<HotPresenter>(), HotContract.View {
    override fun updateHotData(list: List<PearNode>) {
    }

    override fun initPresenter(): HotPresenter {
        return HotPresenter()
    }

    override val contentView: Int
        get() = R.layout.fragment_home_hot

    override fun initViews() {
    }
}