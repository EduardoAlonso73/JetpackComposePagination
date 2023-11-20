package com.mrntlu.pagination.repository
import com.mrntlu.pagination.models.organizations.Association
import com.mrntlu.pagination.service.AssociationApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AssociationManuelPagingRepository @Inject constructor(
    private val newsApiService: AssociationApiService
) {
    suspend fun getAssociation(page: Int): Flow<Association> = flow {
        try {
            emit(newsApiService.getAssociation(page))
        } catch (error: Exception) {
            emit(Association().copy(status = "error"))
        }
    }.flowOn(Dispatchers.IO)
}