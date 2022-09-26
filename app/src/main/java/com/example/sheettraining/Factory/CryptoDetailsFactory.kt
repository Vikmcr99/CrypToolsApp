package com.example.sheettraining.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sheettraining.ViewModel.DetailsCryptoViewModel

class CryptoDetailsFactory(val documentId: String)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsCryptoViewModel(documentId) as T
    }
}