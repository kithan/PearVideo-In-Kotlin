package com.kotlin.kunlun.vmovier_in_kotlin

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.trello.rxlifecycle2.components.support.RxFragment


/**
 * Created by 0000- on 2016/6/11.
 */
abstract class BaseFragment<T : BasePresenter<*>> : RxFragment(), IMvpView {

    lateinit  var presenter: T

    abstract fun initPresenter(): T

    lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(contentView, container,false)
        presenter = initPresenter()
        presenter?.attachView(this )
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    open abstract val contentView: Int

    abstract fun initViews()

    override fun onDestroy() {
        presenter?.detachView()
        super.onDestroy()
    }

    protected fun startActivityWithTransition(intent: Intent, view: View, shareElementResId: Int) {
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, getString(shareElementResId)).toBundle())
    }
}
