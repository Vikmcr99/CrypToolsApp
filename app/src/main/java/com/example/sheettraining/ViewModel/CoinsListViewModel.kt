package com.example.sheettraining.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sheettraining.Dao.CoinDao
import com.example.sheettraining.Model.Coin

class CoinsListViewModel : ViewModel() {
    var coins = MutableLiveData<List<Coin>>()

    var msg = MutableLiveData<String>()


    init {
        CoinDao.listar()
            .addSnapshotListener{snapshot, error ->
                if (error != null){
                    msg.value = error.message
                }

                if (snapshot != null){
                    coins.value = snapshot.toObjects(Coin::class.java)
                }
            }
    }



}
