package id.ac.unpas.managemen_keuangan.networks.responses.transactions

import id.ac.unpas.managemen_keuangan.models.Transaction

data class TransactionGetResponse(
    val data: List<Transaction>
)
