package com.example.sheettraining.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.sheettraining.ViewModel.CryproAddViewModel
import com.example.sheettraining.R
import com.example.sheettraining.databinding.FragmentCryproAddBinding
import com.example.sheettraining.databinding.FragmentCryptoInfoBinding
import com.google.android.material.snackbar.Snackbar

class CryproAddFragment : Fragment() {


    private var _binding: FragmentCryproAddBinding?= null
    private val binding get() = _binding!!
    private lateinit var viewModel: CryproAddViewModel
    var documentId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCryproAddBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(CryproAddViewModel::class.java)
        val view = binding.root

        viewModel.msg
            .observe(viewLifecycleOwner){
                if ( it.isNotBlank()) {
                    showSnackbar(view, it)
                }
            }

        binding.btnAdicionaMov.setOnClickListener{
            val moeda = binding.inputCrypto.text.toString()
            val data = binding.inputData.text.toString()
            val valor = binding.inputValor.text.toString()

            if (moeda.isNotEmpty() && data.isNotEmpty() && valor.isNotEmpty()){
                viewModel.addCrypto(moeda, data, valor)
                findNavController().navigate(R.id.coinsListFragment)
            }

            else
                Toast.makeText(requireContext(), "Por favor insira todos os campos", Toast.LENGTH_LONG).show()
        }

        return view
    }

    private fun showSnackbar(view: View, msg: String) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()
    }


}