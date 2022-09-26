package com.example.sheettraining.Dao

import com.example.sheettraining.Dao.AuthDao.Companion.getCurrentUser
import com.example.sheettraining.Model.Coin
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CoinDao {
    companion object {
        private val collection = Firebase
            .firestore.collection("Cryptos")


        fun listar() = collection

        fun inserir(coin: Coin) = collection.add(coin)

        fun exibir(documentId: String) = collection.document(documentId)


        fun excluir(documentId: String) = collection.document(documentId).delete()

        fun atualizar(documentId: String, coins: Coin) = collection.document(documentId).set(coins)



    }

}