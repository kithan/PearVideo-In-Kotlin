package com.kotlin.pearvideo.data

data class HttpResult2<T,R>(var resultCode: Int = 0,
                          var resultMsg: String? = null,
                          var reqId: String,
                          var SystemTime:Long,
                          var data: T? = null,
                          val data2:R?)