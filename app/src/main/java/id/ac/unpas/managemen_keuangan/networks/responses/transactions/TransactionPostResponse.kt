package id.ac.unpas.managemen_keuangan.networks.responses.transactions

import id.ac.unpas.managemen_keuangan.models.Transaction

data class TransactionPostResponse(
    val message: String,
    val success: Boolean,
    val data: Transaction?
)
