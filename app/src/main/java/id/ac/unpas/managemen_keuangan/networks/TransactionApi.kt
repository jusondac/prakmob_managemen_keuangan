package id.ac.unpas.managemen_keuangan.networks

import com.skydoves.sandwich.ApiResponse
//import id.ac.unpas.agenda.models.Transaction
import id.ac.unpas.managemen_keuangan.networks.responses.transactions.TransactionGetResponse
import id.ac.unpas.managemen_keuangan.networks.responses.transactions.TransactionPostResponse
import id.ac.unpas.managemen_keuangan.networks.responses.transactions.TransactionDeleteResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TransactionApi {
    @GET("todo")
    suspend fun findAll(): ApiResponse<TransactionGetResponse>

    @POST("todo")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body todo: Transaction): ApiResponse<TransactionPostResponse>

    @PUT("todo/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("id") id: String, @Body category: Transaction): ApiResponse<TransactionPostResponse>

    @DELETE("todo/{id}")
    suspend fun delete(@Path("id") id: String): ApiResponse<TransactionDeleteResponse>
}