package id.ac.unpas.managemen_keuangan.networks.responses.categories

//import id.ac.unpas.managemen_keuangan.models.Category

data class CategoryPostResponse(
    val message: String,
    val success: Boolean,
    val data: Category?
)
