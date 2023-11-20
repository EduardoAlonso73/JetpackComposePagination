package com.mrntlu.pagination.models.organizations


import com.google.gson.annotations.SerializedName

data class Organization(
    @SerializedName("ein")
    val ein: Int?,
    @SerializedName("strein")
    val strein: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("sub_name")
    val subName: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("state")
    val state: String?,
    @SerializedName("ntee_code")
    val nteeCode: String?,
    @SerializedName("raw_ntee_code")
    val rawNteeCode: String?,
    @SerializedName("subseccd")
    val subseccd: Int?,
    @SerializedName("has_subseccd")
    val hasSubseccd: Boolean?,
    @SerializedName("have_filings")
    val haveFilings: Any?,
    @SerializedName("have_extracts")
    val haveExtracts: Any?,
    @SerializedName("have_pdfs")
    val havePdfs: Any?,
    @SerializedName("score")
    val score: Int?
)