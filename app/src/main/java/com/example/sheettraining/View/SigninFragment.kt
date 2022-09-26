package com.example.sheettraining.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.sheettraining.Dao.AuthDao.Companion.auth
import com.example.sheettraining.R
import com.example.sheettraining.ViewModel.SigninViewModel
import com.example.sheettraining.databinding.FragmentSigninBinding
import com.google.android.material.snackbar.Snackbar

class SigninFragment : Fragment() {

    private var _binding: FragmentSigninBinding?= null
    private val binding get() = _binding!!
    private lateinit var viewModel: SigninViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSigninBinding.inflate(inflater,container,false)
        //binding.lifecycleOwner = this
        val view = binding.root
        viewModel = ViewModelProvider(this)[SigninViewModel::class.java]

        viewModel.status.observe(viewLifecycleOwner){
            if (it)
                findNavController()
                    .navigate(R.id.homeFragment2)
        }

        viewModel.msg.observe(viewLifecycleOwner){
            if (it.isNotBlank())
                Snackbar.make(view, it, Snackbar.LENGTH_LONG).show()
        }

        binding.btnEntrar.setOnClickListener {
            val email = binding.inputEmail.text.toString()
            val password = binding.inputSenha.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                //viewModel.auth(email,password)
                val task = auth.signInWithEmailAndPassword(email, password)
                task.addOnCompleteListener {
                    if (task.isSuccessful) {
                        val user = auth.currentUser?.email.toString()
                        Toast.makeText(
                            requireContext(),
                            "Bem vindo ${user}",
                            Toast.LENGTH_LONG
                        ).show()

                        findNavController().navigate(R.id.homeFragment2)
                    }

                }
                task.addOnFailureListener{
                    Toast.makeText(requireContext(), "Email ou senha invalidas", Toast.LENGTH_LONG).show()
                }
            }
            else
                Toast.makeText(requireContext(), "todos os campos devem ser preenchidos", Toast.LENGTH_LONG).show()

            }

            binding.tvCadastrar.setOnClickListener {
                findNavController().navigate(R.id.signUp)
            }

            return view
        }}

