package com.kotlin.pearvideo.data

import io.reactivex.observers.DisposableObserver

/**
 * Created by hpb on 2017/9/13.
 */
open class SimpleDisposableObserver<T> :DisposableObserver<T>() {
    override fun onNext(data: T) {
    }

    override fun onComplete() {
    }

    override fun onError(e: Throwable) {
    }
}