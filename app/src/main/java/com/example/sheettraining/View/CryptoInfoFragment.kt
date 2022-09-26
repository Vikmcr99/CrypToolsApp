package com.example.sheettraining.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.sheettraining.Api.Endpoint
import com.example.sheettraining.ViewModel.CryptoInfoViewModel
import com.example.sheettraining.CryptoClient.NetworkUtils
import com.example.sheettraining.databinding.FragmentCryptoInfoBinding
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response

class CryptoInfoFragment : Fragment() {

    private var _binding: FragmentCryptoInfoBinding?= null
    private val binding get() = _binding!!
    private lateinit var viewModel: CryptoInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCryptoInfoBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(CryptoInfoViewModel::class.java)

        getCurrencyCoin()
        getCurrencies()

        return binding.root
    }


    fun getCurrencyCoin(){
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://cdn.jsdelivr.net/")
        val endpoint = retrofitClient.create(Endpoint::class.java)

        binding.ivCardano.setOnClickListener{
            endpoint.getCurrencyCoin("ada", "brl").enqueue(object : retrofit2.Callback<JsonObject>
            {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    var currentValor = response.body()?.entrySet()?.find { it.key == "brl" }
                    var currentdata = response.body()?.entrySet()?.find { it.key == "date" }
                    binding.tvValor.setText(currentValor.toString())
                    binding.tvData.setText(currentdata.toString())

                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    println("error")
                }
            })
        }

        binding.ivBitcoin.setOnClickListener{
            endpoint.getCurrencyCoin("btc", "brl").enqueue(object : retrofit2.Callback<JsonObject>
            {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    var currentValor = response.body()?.entrySet()?.find { it.key == "brl" }
                    var currentdata = response.body()?.entrySet()?.find { it.key == "date" }
                    binding.tvValor.setText(currentValor.toString())
                    binding.tvData.setText(currentdata.toString())

                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    println("error")
                }
            })
        }

        binding.ivEthereum.setOnClickListener{
            endpoint.getCurrencyCoin("eth", "brl").enqueue(object : retrofit2.Callback<JsonObject>
            {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    var currentValor = response.body()?.entrySet()?.find { it.key == "brl" }
                    var currentdata = response.body()?.entrySet()?.find { it.key == "date" }
                    binding.tvValor.setText(currentValor.toString())
                    binding.tvData.setText(currentdata.toString())

                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    println("error")
                }
            })
        }

        binding.ivDolar.setOnClickListener{
            endpoint.getCurrencyCoin("usd", "brl").enqueue(object : retrofit2.Callback<JsonObject>
            {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    var currentValor = response.body()?.entrySet()?.find { it.key == "brl" }
                    var currentdata = response.body()?.entrySet()?.find { it.key == "date" }
                    binding.tvValor.setText(currentValor.toString())
                    binding.tvData.setText(currentdata.toString())

                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    println("error")
                }
            })
        }


    }

    fun getCurrencies(){
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://cdn.jsdelivr.net/")
        val endpoint = retrofitClient.create(Endpoint::class.java)

        endpoint.getCurrencies().enqueue(object : retrofit2.Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                var data = mutableListOf<String>()

                response.body()?.keySet()?.iterator()?.forEach {
                    data.add(it)
                }

                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, data)


            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                println("Error")
            }

        })



    }
}
