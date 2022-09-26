package com.example.sheettraining.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.sheettraining.Dao.AuthDao
import com.example.sheettraining.Dao.AuthDao.Companion.auth
import com.example.sheettraining.R
import com.example.sheettraining.ViewModel.SignUpViewModel
import com.example.sheettraining.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUp : Fragment() {

    private var _binding: FragmentSignUpBinding?= null
    private val binding get() = _binding!!
    private lateinit var viewModel: SignUpViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

        binding.btnCadastrar.setOnClickListener{
            auth = FirebaseAuth.getInstance()
            val email = binding.inputEmail.text.toString()
            val senha = binding.inputSenha.text.toString()
            val confirmaSenha = binding.inputConfirmaSn.text.toString()

            if (email.isNotEmpty() && senha.isNotEmpty() && confirmaSenha.isNotEmpty()){
                if (senha == confirmaSenha) {
                    //viewModel.register(email,senha)
                    val task = auth.createUserWithEmailAndPassword(email, senha)
                    task.addOnCompleteListener{
                        if (task.isSuccessful){
                            val user = AuthDao.auth.currentUser?.email.toString()
                            Toast.makeText(
                                requireContext(),
                                "Bem vindo ${user}",
                                Toast.LENGTH_LONG
                            ).show()
                            findNavController().navigate(R.id.homeFragment2)
                        }
                    }.addOnFailureListener{
                        Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_LONG).show()
                    }
                }
                else{
                    Toast.makeText(requireContext(), "Senhas não conferem", Toast.LENGTH_LONG).show()

                }

            }
            else{
                Toast.makeText(requireContext(), "todos os campos devem ser preenchidos", Toast.LENGTH_LONG).show()
            }
        }


        return binding.root
    }

}