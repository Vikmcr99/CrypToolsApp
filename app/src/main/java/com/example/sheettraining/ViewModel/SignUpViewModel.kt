package com.example.sheettraining.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    val status = MutableLiveData<Boolean>()
    val msg = MutableLiveData<String>()

    init {
        status.value = false
    }

    fun register(email: String, senha: String){

        val task = com.example.sheettraining.Dao.AuthDao.registerUser(email, senha)
        task.addOnSuccessListener {
            status.value = true
        }.addOnFailureListener {
            msg.value = it.message
        }

    }
}