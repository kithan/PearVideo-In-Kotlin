package com.kotlin.pearvideo.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import devlight.io.library.ntb.NavigationTabBar
import java.lang.ref.WeakReference
import android.support.v4.util.SparseArrayCompat
import android.view.ViewGroup


/**
 * Created by hpb on 2017/9/14.
 */
class HomeTabAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    lateinit var mPages: FragmentPagerItems
    private val holder: SparseArrayCompat<WeakReference<Fragment>>? = null


    constructor(fm: FragmentManager?, pages: FragmentPagerItems) : this(fm) {
        mPages = pages
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        val item = super.instantiateItem(container, position)
        if (item is Fragment) {
            holder?.put(position, WeakReference(item))
        }
        return item
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        holder?.remove(position)
        super.destroyItem(container, position, `object`)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mPages[position].title
    }

    override fun getCount(): Int {
        return mPages?.size
    }

    override fun getItem(position: Int): Fragment? {
        return mPages[position].instantiate(mPages.context, position)
    }
}