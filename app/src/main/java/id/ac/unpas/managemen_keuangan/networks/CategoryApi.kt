package id.ac.unpas.managemen_keuangan.networks

import com.skydoves.sandwich.ApiResponse
import id.ac.unpas.managemen_keuangan.models.Category
import id.ac.unpas.managemen_keuangan.networks.responses.categories.CategoryGetResponse
import id.ac.unpas.managemen_keuangan.networks.responses.categories.CategoryPostResponse
import id.ac.unpas.managemen_keuangan.networks.responses.categories.CategoryDeleteResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CategoryApi {
    @GET("category")
    suspend fun findAll(): ApiResponse<CategoryGetResponse>

    @POST("category")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body todo: Category): ApiResponse<CategoryPostResponse>

    @PUT("category/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("id") id: String, @Body category: Category): ApiResponse<CategoryPostResponse>

    @DELETE("category/{id}")
    suspend fun delete(@Path("id") id: String): ApiResponse<CategoryDeleteResponse>
}