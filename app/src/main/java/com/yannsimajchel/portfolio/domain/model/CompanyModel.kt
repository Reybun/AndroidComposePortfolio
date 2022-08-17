package com.yannsimajchel.portfolio.domain.model

data class CompanyModel(
    /**
     *
     */
    val name: String,
    /**
     *
     */
    val role: String,
    /**
     *
     */
    val period: String,
    /**
     *
     */
    val missionList: List<String>,
    /**
     *
     */
    val skillsList: List<String>,
    /**
     *
     */
    val imageRes: Int,
)