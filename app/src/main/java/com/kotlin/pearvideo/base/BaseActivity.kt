package com.kotlin.kunlun.vmovier_in_kotlin

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.transition.Slide
import android.view.Gravity
import android.view.View
import com.kotlin.pearvideo.R

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.vise.xsnow.event.BusManager


/**
 * Created by 0000- on 2016/6/11.
 */
abstract class BaseActivity<P : IPresenter<*>> : RxAppCompatActivity(), IMvpView {

    var presenter: P? = null

    abstract fun initPresenter(): P?


    abstract fun initTitle(): String?

    protected var mToolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val slideTransition = Slide()
            slideTransition.slideEdge = Gravity.LEFT
            slideTransition.duration = 500
            window.reenterTransition = slideTransition
            window.exitTransition = slideTransition
        }
        super.onCreate(savedInstanceState)
        setContentView(contentView)
        if (isRegisterEvent) {
            BusManager.getBus().register(this)
        }
        var view = findViewById(R.id.toolbar)
        if (view != null) mToolbar = view as Toolbar
        val title = initTitle()
        if (mToolbar != null) {
            mToolbar!!.setTitleTextAppearance(this, android.R.style.TextAppearance_Small)
            if (!TextUtils.isEmpty(title)) {
                supportActionBar!!.setTitle(title)
                setSupportActionBar(mToolbar)
            }
        }

//        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.colorPrimary))
        presenter = initPresenter()
        presenter?.attachView(this as Nothing)
        initViews()
    }


    protected fun initToolBar(title: String?) {
        if (mToolbar != null) {
            mToolbar!!.title = title
            setSupportActionBar(mToolbar)
//            mToolbar!!.setNavigationIcon(R.drawable.back_icon)
            mToolbar!!.setNavigationOnClickListener { finish() }
        }

    }

    open val isRegisterEvent: Boolean
        get() = false

    open abstract val contentView: Int

    abstract fun initViews()

    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
    }

    override fun startActivity(intent: Intent, options: Bundle?) {
        super.startActivity(intent, options)
    }

    override fun onDestroy() {
        if (presenter != null) {
            presenter!!.detachView()
        }
        super.onDestroy()
    }

    protected fun startActivityWithTransition(intent: Intent, view: View, shareElementResId: Int) {
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, getString(shareElementResId)).toBundle())
    }

    //extend fun :toast
    fun Activity.Toast(message: CharSequence, duration: Int = android.widget.Toast.LENGTH_SHORT) {
        android.widget.Toast.makeText(this, message, duration).show();
    }
}
