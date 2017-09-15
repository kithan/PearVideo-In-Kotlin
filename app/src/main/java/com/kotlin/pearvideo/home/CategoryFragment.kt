package com.kotlin.pearvideo.home

import com.kotlin.kunlun.vmovier_in_kotlin.BaseFragment
import com.kotlin.pearvideo.R

class CategoryFragment : BaseFragment<CategoryPresenter>(), CategoryContract.View {
    override fun initPresenter(): CategoryPresenter {
        return CategoryPresenter()
    }

    override val contentView: Int
        get() = R.layout.fragment_home_category

    override fun initViews() {
    }
}