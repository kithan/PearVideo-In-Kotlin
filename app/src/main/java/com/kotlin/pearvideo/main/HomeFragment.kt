package com.kotlin.pearvideo.main

import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kotlin.kunlun.vmovier_in_kotlin.BaseFragment
import com.kotlin.pearvideo.R
import com.kotlin.pearvideo.home.CategoryFragment
import com.kotlin.pearvideo.home.HomeTabAdapter
import com.kotlin.pearvideo.home.HotFragment
import com.kotlin.pearvideo.home.RecommendFragment
import com.kotlin.pearvideo.model.Category
import com.kotlin.pearvideo.model.PearNode
import com.ogaclejapan.smarttablayout.SmartTabLayout
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import com.vise.log.ViseLog
import devlight.io.library.ntb.NavigationTabBar
import kotlinx.android.synthetic.main.fragment_main_home.*

/**
 * Created by hpb on 2017/9/14.
 */
class HomeFragment : BaseFragment<HomePresenter>(), HomeContract.View {

    override fun setCategoryTabs(tabCategory: List<Category>) {
        val models = ArrayList<NavigationTabBar.Model>()
        val pages = FragmentPagerItems(context)
        pages.add(FragmentPagerItem.of("热门", HotFragment::class.java))
        pages.add(FragmentPagerItem.of("推荐", RecommendFragment::class.java))
        val transResId = android.R.color.transparent
        models.add(NavigationTabBar.Model.Builder(null, resources.getColor(transResId))
                .title("热门").build())
        models.add(NavigationTabBar.Model.Builder(null, resources.getColor(transResId))
                .title("推荐").build())
        tabCategory.mapTo(models) {
            pages.add(FragmentPagerItem.of(it.name, CategoryFragment::class.java))
            NavigationTabBar.Model.Builder(null, resources.getColor(transResId))
                    .title(it.name).build()
        }
        var adapter = HomeTabAdapter(childFragmentManager, pages)
        home_vp.adapter = adapter
        ntb_category.setViewPager(home_vp)
        home_vp.currentItem = 0
        var lastPos = 0
        ntb_category.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                setTabTextSize(position, true)
                if (lastPos != position) {
                    setTabTextSize(lastPos, false)
                    lastPos = position
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }
        })

    }

    fun setTabTextSize(position: Int, selected: Boolean) {
        var tabText = ntb_category.getTabAt(position).findViewById(R.id.custom_text) as TextView
        if( selected){
            tabText.typeface=Typeface.DEFAULT_BOLD
        }else{
            tabText.typeface = Typeface.DEFAULT
        }
    }

    override fun initPresenter(): HomePresenter {
        return HomePresenter()
    }

    override val contentView: Int
        get() = R.layout.fragment_main_home

    override fun initViews() {
        presenter.getCategory()
    }
}