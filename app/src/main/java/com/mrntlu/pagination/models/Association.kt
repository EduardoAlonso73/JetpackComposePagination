package com.mrntlu.pagination.models.organizations


import com.google.gson.annotations.SerializedName

data class Association(
    val status: String="ok",
    @SerializedName("total_results")
    val totalResults: Int? = null,
    @SerializedName("organizations")
    val organizations: List<Organization>? = null,
    @SerializedName("num_pages")
    val numPages: Int? = null,
    @SerializedName("cur_page")
    val curPage: Int? = null,
    @SerializedName("page_offset")
    val pageOffset: Int? = null,
    @SerializedName("per_page")
    val perPage: Int? = null,
    @SerializedName("search_query")
    val searchQuery: Any? = null,
    @SerializedName("selected_state")
    val selectedState: Any? = null,
    @SerializedName("selected_ntee")
    val selectedNtee: Any? = null,
    @SerializedName("selected_code")
    val selectedCode: Any? = null,
    @SerializedName("data_source")
    val dataSource: String? = null,
    @SerializedName("api_version")
    val apiVersion: Int? = null
)