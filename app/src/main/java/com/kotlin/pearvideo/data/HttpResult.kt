package com.kotlin.kunlun.vmovier_in_kotlin.data

data class HttpResult<T>(var resultCode: Int = 0,
                         var resultMsg: String? = null,
                         var reqId: String,
                         var SystemTime:Long,
                         var data: T? = null)