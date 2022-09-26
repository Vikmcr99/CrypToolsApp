package com.example.sheettraining.Dao

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class AuthDao {
    companion object {

        val auth = Firebase.auth

        fun getCurrentUser() = auth.currentUser

        fun deslogar(){
            auth.signOut()
        }


        fun registerUser(
            email: String,
            senha: String): Task<AuthResult> {

            return auth.createUserWithEmailAndPassword(email, senha)
        }

        fun authUser(
            email: String,
            senha: String): Task<AuthResult> {

            return auth.signInWithEmailAndPassword(email, senha)
        }


        fun atualizarUsuario(nome: String): Task<Void> {
            val user = getCurrentUser()
            val profileUpdate = userProfileChangeRequest {
                displayName = nome
            }
            return user!!.updateProfile(profileUpdate)
        }
    }
}