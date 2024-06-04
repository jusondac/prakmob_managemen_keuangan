package id.ac.unpas.managemen_keuangan.networks

import com.skydoves.sandwich.ApiResponse
import id.ac.unpas.managemen_keuangan.models.Transaction
import id.ac.unpas.managemen_keuangan.models.User
import id.ac.unpas.managemen_keuangan.networks.responses.users.UserDeleteResponse
import id.ac.unpas.managemen_keuangan.networks.responses.users.UserGetResponse
import id.ac.unpas.managemen_keuangan.networks.responses.users.UserPostResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApi {
    @GET("user")
    suspend fun findAll(): ApiResponse<UserGetResponse>

    @POST("user")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body user: User): ApiResponse<UserPostResponse>

    @PUT("user/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("id") id: String, @Body user: User): ApiResponse<UserPostResponse>

    @DELETE("user/{id}")
    suspend fun delete(@Path("id") id: String): ApiResponse<UserDeleteResponse>
}