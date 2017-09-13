package com.kotlin.pearvideo.model

data class PearNode(
	val nodeName: String? = null,
	val nodeLogo: String? = null,
	val moreId: String? = null,
	val nodeDesc: String? = null,
	val nodeType: String? = null,
	val isOrder: String? = null,
    val contList:List<PearCont>
)
