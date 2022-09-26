package com.example.sheettraining.ViewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sheettraining.Dao.AuthDao
import com.example.sheettraining.Dao.CoinDao
import com.example.sheettraining.Model.Coin

class DetailsCryptoViewModel(val documentId: String) : ViewModel() {

    val crypto = MutableLiveData<Coin>()
    val status = MutableLiveData<Boolean>()
    val msg = MutableLiveData<String>()

    init {
        CoinDao.exibir(documentId)
            .addSnapshotListener { snapshot, error ->
                if (error != null)
                    msg.value = error.message
                if (snapshot != null)
                    crypto.value = snapshot.toObject(com.example.sheettraining.Model.Coin::class.java)

            }
    }


    fun excluir()  {
        val task = CoinDao.excluir(documentId)
        task.addOnSuccessListener {
            status.value = true
        }.addOnFailureListener {
            msg.value = it.message
        }

    }

    fun atualizar(moeda: String, data: String, valor: String) {

        val userUid = AuthDao.getCurrentUser()!!.uid
        val crypto = Coin(moeda, data, valor, userUid)

        val task = CoinDao.atualizar(documentId!!, crypto)
        task.addOnSuccessListener {
            msg.value = "Registro atualizado com sucesso"
            status.value = true
            }.addOnFailureListener {
                msg.value = it.message
            }


    }
}