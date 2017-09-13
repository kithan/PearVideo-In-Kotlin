package com.kotlin.pearvideo.model

data class PearArea(
        val expInfo: ExpInfo? = null,
        val areaId: String? = null
) {
    inner class ExpInfo(
            val sValue: String? = null,
            val frontExpId: String? = null,
            val algorighmExpId: String? = null
    )
}


