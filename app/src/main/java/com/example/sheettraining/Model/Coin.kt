package com.example.sheettraining.Model

import com.google.firebase.firestore.DocumentId

class Coin(
    val moeda: String? = null,
    val data: String? = null,
    val valor: String? = null,
    val userUId: String? = null,

    @DocumentId
    val documentId: String? = null
)
{
    override fun toString() = "${moeda}"
}