package com.mrntlu.pagination.service

import com.mrntlu.pagination.models.organizations.Association
import retrofit2.http.GET
import retrofit2.http.Query


interface AssociationApiService {


    @GET("nonprofits/api/v2/search.json?")
    suspend fun getAssociation(
        @Query("page") page: Int
    ): Association
}