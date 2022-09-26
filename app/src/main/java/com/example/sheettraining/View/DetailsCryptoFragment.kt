package com.example.sheettraining.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.sheettraining.Factory.CryptoDetailsFactory
import com.example.sheettraining.R
import com.example.sheettraining.ViewModel.DetailsCryptoViewModel
import com.example.sheettraining.databinding.FragmentCryproAddBinding
import com.example.sheettraining.databinding.FragmentDetailsCryptoBinding

class DetailsCryptoFragment : Fragment() {

    private var _binding: FragmentDetailsCryptoBinding?= null
    private val binding get() = _binding!!
    private lateinit var viewModel: DetailsCryptoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsCryptoBinding.inflate(inflater,container,false)
        val view = binding.root
        val documentId = arguments?.getString("documentId")
        val factory = CryptoDetailsFactory(documentId!!)
        viewModel = ViewModelProvider(this, factory).get(DetailsCryptoViewModel::class.java)

        viewModel.crypto.observe(viewLifecycleOwner){
            if (it != null){
                view.findViewById<TextView>(R.id.inputCrypto).text = it.moeda
                view.findViewById<TextView>(R.id.inputValor).text = it.valor
                view.findViewById<TextView>(R.id.inputData).text = it.data

            }
        }

        binding.btnSaveEdits.setOnClickListener{
            val moeda = binding.inputCrypto.text.toString()
            val data = binding.inputData.text.toString()
            val valor = binding.inputValor.text.toString()

            if (moeda.isNotEmpty() && data.isNotEmpty() && valor.isNotEmpty()){
                viewModel.atualizar(moeda, data, valor)
                findNavController().navigate(R.id.coinsListFragment)
            }

            else
                Toast.makeText(requireContext(), "Por favor insira todos os campos", Toast.LENGTH_LONG).show()
        }

        binding.floatbDeletaRegistro.setOnClickListener{
            viewModel.excluir()
            Toast.makeText(requireContext(), "Registro excluido", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.coinsListFragment)
        }

        return view
    }


}