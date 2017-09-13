package com.kotlin.pearvideo.model

/**
 * Created by hpb on 2017/9/13.
 */
data class HttpLiveCont(var nextUrl: String, var topList: List<PearCont>, var contList: List<PearCont>) : BaseModel()