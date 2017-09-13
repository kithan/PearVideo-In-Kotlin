package com.kotlin.fivehundred.a500px_in_kotlin.data

import com.kotlin.kunlun.vmovier_in_kotlin.data.HttpResult
import com.kotlin.pearvideo.data.HttpResult2
import com.kotlin.pearvideo.data.PearVideoException
import com.kotlin.pearvideo.data.TransitionResult
import com.kotlin.pearvideo.model.*
import com.trello.rxlifecycle2.LifecycleProvider
import com.vise.log.ViseLog
import com.vise.xsnow.http.ViseHttp
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.functions.Function
import io.reactivex.functions.Predicate
import io.reactivex.observers.DisposableObserver
import org.json.JSONArray


/**
 * Created by hpb on 2017/9/12.
 */
class Repository private constructor() {

    companion object {
        val instance = Repository()
    }

    private val getCategorys = "getCategorys.jsp"
    private val domainList = "domainList.jsp>type="
    private val hotUrl = "home.jsp?lastLikeIds=&isHome=1"
    private val recommendUrl = "getVodConts.jsp?isHome=0&channelCode=&start="
    private val liveListUrl = "getLiveConts.jsp?start="

    private inner class MyTransformer<T> : ObservableTransformer<HttpResult<T>, T> {
        var provider: LifecycleProvider<*>? = null

        constructor(provider: LifecycleProvider<*>) {
            this@MyTransformer.provider = provider
        }

        override fun apply(upstream: Observable<HttpResult<T>>): ObservableSource<T> {
            val tObservable = upstream!!.flatMap(Function<HttpResult<T>, ObservableSource<T>> { tHttpResult ->
                if (tHttpResult.resultCode !== 1) {
                    return@Function Observable.error(PearVideoException(
                            "status:" + tHttpResult.resultCode + "message:" + tHttpResult.resultMsg))
                }
                Observable.create { e ->
                    e.onNext(tHttpResult.data!!)
                    e.onComplete()
                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .onTerminateDetach()
            tObservable.compose(provider?.bindToLifecycle())
            return tObservable
        }
    }


    private inner class BaseTransformer<T : BaseModel> : ObservableTransformer<T, T> {
        var provider: LifecycleProvider<*>? = null

        constructor(provider: LifecycleProvider<*>) {
            this@BaseTransformer.provider = provider
        }

        override fun apply(upstream: Observable<T>): ObservableSource<T> {
            val tObservable = upstream!!.flatMap(Function<T, ObservableSource<T>> { tHttpResult ->
                if (tHttpResult.resultCode !== 1) {
                    return@Function Observable.error(PearVideoException(
                            "status:" + tHttpResult.resultCode + "message:" + tHttpResult.resultMsg))
                }
                Observable.create { e ->
                    e.onNext(tHttpResult)
                    e.onComplete()
                }
            }).subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .onTerminateDetach()
            tObservable.compose(provider?.bindToLifecycle())
            return tObservable
        }
    }

    private inner class MyTransformer2<T, R> : ObservableTransformer<HttpResult2<T, R>, TransitionResult<T, R>> {
        var provider: LifecycleProvider<*>? = null

        constructor(provider: LifecycleProvider<*>) {
            this@MyTransformer2.provider = provider
        }

        override fun apply(upstream: Observable<HttpResult2<T, R>>): ObservableSource<TransitionResult<T, R>> {
            val tObservable = upstream!!.flatMap(Function<HttpResult2<T, R>, ObservableSource<TransitionResult<T, R>>> { tHttpResult ->
                if (tHttpResult.resultCode !== 1) {
                    return@Function Observable.error(PearVideoException(
                            "status:" + tHttpResult.resultCode + "message:" + tHttpResult.resultMsg))
                }
                Observable.create { e ->
                    e.onNext(TransitionResult(tHttpResult.data, tHttpResult.data2))
                    e.onComplete()
                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .onTerminateDetach()
            tObservable.compose(provider?.bindToLifecycle())
            return tObservable
        }
    }

    fun getCategorys(provider: LifecycleProvider<*>, observer: DisposableObserver<List<Category>>) {
        ViseHttp.GET(getCategorys).request<HttpCategory>(HttpCategory::class.java)
                .compose(BaseTransformer<HttpCategory>(provider))
                .map({ tHttpCategory -> tHttpCategory.categoryList!! })
                .subscribe(observer)
    }

    fun getDomainList(type: String, provider: LifecycleProvider<*>, observer: DisposableObserver<List<PearDomain>>) {
        ViseHttp.GET(domainList + type).request<HttpDomain>(HttpDomain::class.java)
                .compose(BaseTransformer<HttpDomain>(provider))
                .map({ tHttpDomain -> tHttpDomain.domainList!! })
                .subscribe(observer)
    }

    fun getHotList(start: Int, provider: LifecycleProvider<*>, observer: DisposableObserver<List<PearNode>>) {
        ViseHttp.GET(hotUrl + start).request<HttpPearNode>(HttpPearNode::class.java)
                .compose(BaseTransformer<HttpPearNode>(provider))
                .map({ tHttpDomain -> tHttpDomain.dataList!! })
                .subscribe(observer)
    }
    fun getLiveList(start: Int, provider: LifecycleProvider<*>, observer: DisposableObserver<HttpLiveCont>) {
        ViseHttp.GET(liveListUrl + start).request<HttpLiveCont>(HttpLiveCont::class.java)
                .compose(BaseTransformer<HttpLiveCont>(provider))
                .subscribe(observer)
    }


}