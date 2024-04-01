package com.faisal.uts_mobile_app

import com.google.gson.annotations.SerializedName

data class UserListResponse(
    val page: Int,
    @SerializedName("per_page") val perPage: Int,
    val total: Int,
    @SerializedName("total_pages") val totalPages: Int,
    val data: List<User>,
    val support: Support
)

data class Support(
    val url: String,
    val text: String
)

