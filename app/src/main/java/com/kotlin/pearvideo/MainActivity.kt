package com.kotlin.pearvideo

import android.os.Bundle
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import devlight.io.library.ntb.NavigationTabBar
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v4.view.PagerAdapter
import android.util.SparseArray
import android.view.View
import com.kotlin.fivehundred.a500px_in_kotlin.data.Repository
import com.kotlin.pearvideo.data.SimpleDisposableObserver
import com.kotlin.pearvideo.model.Category
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.vise.log.ViseLog


class MainActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        Repository.instance.getCategorys(this, object : SimpleDisposableObserver<List<Category>>() {
            override fun onNext(t: List<Category>) {
                for (c in t) {
                    ViseLog.d(c.toString())
                }
            }

            override fun onError(e: Throwable) {
            }
        })
    }

    private fun initUI() {
        val viewPager = findViewById(R.id.vp_horizontal_ntb) as ViewPager
        viewPager.adapter = object : PagerAdapter() {
            override fun getCount(): Int {
                return 5
            }

            override fun isViewFromObject(view: View, `object`: Any): Boolean {
                return view == `object`
            }

            override fun destroyItem(container: View, position: Int, `object`: Any) {
                (container as ViewPager).removeView(`object` as View)
            }

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                val view = LayoutInflater.from(
                        baseContext).inflate(R.layout.item_vp_list, null, false)

                val recyclerView = view.findViewById(R.id.rv) as RecyclerView
                recyclerView.setHasFixedSize(true)
                recyclerView.layoutManager = LinearLayoutManager(
                        baseContext, LinearLayoutManager.VERTICAL, false
                )
                recyclerView.adapter = RecycleAdapter()

                container.addView(view)
                return view
            }
        }

        val colors = resources.getStringArray(R.array.default_preview)

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

    inner class RecycleAdapter : RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(baseContext).inflate(R.layout.item_list, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.vpItem.text = String.format("Navigation Item #%d", position)
        }

        override fun getItemCount(): Int {
            return 20
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var vpItem: TextView = itemView.findViewOften(R.id.vpItem)
        }

        fun <T : View> View.findViewOften(viewId: Int): T {
            var viewHolder: SparseArray<View> = tag as? SparseArray<View> ?: SparseArray()
            tag = viewHolder
            var childView: View? = viewHolder.get(viewId)
            if (null == childView) {
                childView = findViewById(viewId)
                viewHolder.put(viewId, childView)
            }
            return childView as T
        }
    }
}
