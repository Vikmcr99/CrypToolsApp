package com.example.sheettraining.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sheettraining.Dao.AuthDao
import com.example.sheettraining.Dao.CoinDao
import com.example.sheettraining.Model.Coin


class CryproAddViewModel : ViewModel() {
    var crypto = MutableLiveData<Coin>()
    var status = MutableLiveData<Boolean>()
    var msg = MutableLiveData<String>()
    var documentId: String? = null



    fun addCrypto(moeda: String, data: String, valor: String) {
        val userUid = AuthDao.getCurrentUser()!!.uid
        val crypto = Coin(moeda, data, valor, userUid)
        if (documentId == null) {
            val task = CoinDao.inserir(crypto)
            task.addOnSuccessListener {
                msg.value = "Seu registro foi adicionado com sucesso"
                status.value = true
            }.addOnFailureListener {
                msg.value = it.message
            }
        } else {
            val task = CoinDao.atualizar(documentId!!, crypto)
            task.addOnSuccessListener {
                msg.value = "Registro atualizado"
                status.value = true
            }.addOnFailureListener {
                msg.value = it.message
            }
        }
}
}