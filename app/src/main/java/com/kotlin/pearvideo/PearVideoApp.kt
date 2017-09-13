package com.kotlin.pearvideo

import android.app.Application
import android.os.Build
import com.bumptech.glide.Glide
import com.vise.log.ViseLog
import com.vise.log.inner.LogcatTree
import com.vise.xsnow.common.ViseConfig
import com.vise.xsnow.http.ViseHttp
import com.vise.xsnow.http.interceptor.HttpLogInterceptor
import com.vise.xsnow.http.interceptor.NoCacheInterceptor
import com.vise.xsnow.loader.LoaderManager
import okhttp3.Cache
import okhttp3.ConnectionPool
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

/**
 * Created by hpb on 2017/9/12.
 */
class PearVideoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ViseLog.getLogConfig()
                .configAllowLog(true)//是否输出日志
                .configShowBorders(false)//是否排版显示
        ViseLog.plant(LogcatTree())//添加打印日志信息到Logcat的树
//        DbHelper.instance.init(this)
        LoaderManager.getLoader().init(this)
        ViseHttp.init(this)
      var headers: HashMap<String, String> =HashMap<String, String>()
       headers.put("X-Client-Version","3.2.5")
       headers.put("X-Channel-Code","official")
       headers.put("X-Client-ID","123456789123456")
       headers.put("X-Long-Token","")
       headers.put("X-Platform-Type","0")
       headers.put("X-Platform-Type","0")
       headers.put("X-Platform-Version",Build.VERSION.RELEASE)
       headers.put("X-Serial-Num","1505274633")
       headers.put("X-Client-Hash","2f3d6ffkda95dlz2fhju8d3s6dfges3t")
       headers.put("X-Client-Agent",Build.MANUFACTURER+"-"+Build.MODEL+"-Android "+Build.VERSION.RELEASE)
        ViseHttp.CONFIG()
                .baseUrl(BuildConfig.API_HOST)
                //配置全局请求头
                .globalHeaders(headers)
//                .globalParams(HashMap<String, String>())
                .readTimeout(30)
                .writeTimeout(30)
                .connectTimeout(30)
                .retryCount(3)
                .setHttpCache(true)
                .setHttpCacheDirectory(File(ViseHttp.getContext().cacheDir, ViseConfig.CACHE_HTTP_DIR))
                .httpCache(Cache(File(ViseHttp.getContext().cacheDir, ViseConfig.CACHE_HTTP_DIR), ViseConfig.CACHE_MAX_SIZE))
                //配置自定义离线缓存
                .cacheOffline(Cache(File(ViseHttp.getContext().cacheDir, ViseConfig.CACHE_HTTP_DIR), ViseConfig.CACHE_MAX_SIZE))
                .cacheOnline(Cache(File(ViseHttp.getContext().cacheDir, ViseConfig.CACHE_HTTP_DIR), ViseConfig.CACHE_MAX_SIZE))
                //                .postGzipInterceptor()
                //配置应用级拦截器
                .interceptor(HttpLogInterceptor()
                        .setLevel(HttpLogInterceptor.Level.BODY))
                .networkInterceptor(NoCacheInterceptor())
                .converterFactory(GsonConverterFactory.create())
                .callAdapterFactory(RxJava2CallAdapterFactory.create())
                .connectionPool(ConnectionPool())
    }


    override fun onLowMemory() {
        super.onLowMemory()
        Glide.with(this).onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Glide.with(this).onTrimMemory(level)
    }
}