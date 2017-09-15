package com.kotlin.pearvideo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.kotlin.pearvideo.home.LiveFragment
import com.kotlin.pearvideo.home.SubscribeFragment
import com.kotlin.pearvideo.main.HomeFragment
import com.kotlin.pearvideo.main.MyFragment
import com.kotlin.pearvideo.main.PaikeFragment
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import devlight.io.library.ntb.NavigationTabBar


class MainActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    private fun initUI() {
        val viewPager = findViewById(R.id.vp_horizontal_ntb) as ViewPager
        viewPager.adapter = object:FragmentPagerAdapter(supportFragmentManager){
            private var mFragments: ArrayList<Fragment> = ArrayList<Fragment>(5)

            init {
                mFragments.add(HomeFragment())
                mFragments.add(LiveFragment())
                mFragments.add(SubscribeFragment())
                mFragments.add(PaikeFragment())
                mFragments.add(MyFragment())
            }

            override fun getCount(): Int {
                return 5
            }

            override fun getItem(position: Int): Fragment? {
                return mFragments[position]
            }
        }
        val navigationTabBar = findViewById(R.id.ntb_horizontal) as NavigationTabBar
        val models = ArrayList<NavigationTabBar.Model>()
        models.add(
                NavigationTabBar.Model.Builder(
                        resources.getDrawable(R.drawable.main_tab_toppage),
                        resources.getColor(android.R.color.transparent))
                        .title("首页")
                        .build()
        )
        models.add(
                NavigationTabBar.Model.Builder(
                        resources.getDrawable(R.drawable.main_tab_live),
                        resources.getColor(android.R.color.transparent))
                        .title("直播")
                        .build()
        )
        models.add(
                NavigationTabBar.Model.Builder(
                        resources.getDrawable(R.drawable.main_tab_subscribe),
                        resources.getColor(android.R.color.transparent))
                        .title("订阅")

                        .build()
        )
        models.add(
                NavigationTabBar.Model.Builder(
                        resources.getDrawable(R.drawable.main_tab_paike),
                        resources.getColor(android.R.color.transparent))
                        .title("拍客")
                        .build()
        )
        models.add(
                NavigationTabBar.Model.Builder(
                        resources.getDrawable(R.drawable.main_tab_my),
                        resources.getColor(android.R.color.transparent))
                        .title("我的")
                        .build()
        )
        navigationTabBar.models = models
        navigationTabBar.activeColor = resources.getColor(R.color.colorApp)
        navigationTabBar.setViewPager(viewPager, 0)

        navigationTabBar.isBehaviorEnabled = true
    }
}
