package id.ac.unpas.managemen_keuangan.networks.responses.users

import id.ac.unpas.managemen_keuangan.models.User

data class UserPostResponse(
    val message: String,
    val success: Boolean,
    val data: User?
)
