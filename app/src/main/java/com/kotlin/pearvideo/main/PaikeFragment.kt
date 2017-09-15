package com.kotlin.pearvideo.main

import com.kotlin.kunlun.vmovier_in_kotlin.BaseFragment
import com.kotlin.pearvideo.R

class PaikeFragment : BaseFragment<PaikePresenter>(), PaikeContract.View {
    override fun initPresenter(): PaikePresenter {
        return PaikePresenter()
    }

    override val contentView: Int
        get() = R.layout.fragment_main_paike

    override fun initViews() {
    }


    companion object {

        fun newInstance(): PaikeFragment {
            return PaikeFragment()
        }
    }

}
