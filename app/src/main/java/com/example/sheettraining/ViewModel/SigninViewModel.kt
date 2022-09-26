package com.example.sheettraining.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SigninViewModel() : ViewModel() {

    val status = MutableLiveData<Boolean>()
    val msg = MutableLiveData<String>()

    init {
        status.value = false
    }

    fun auth(email: String, senha: String){

        val task = com.example.sheettraining.Dao.AuthDao.authUser(email, senha)
        task.addOnSuccessListener {
            status.value = true

        }.addOnFailureListener {
            msg.value = it.message
        }

    }
}