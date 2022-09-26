package com.example.sheettraining.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sheettraining.Adapter.CoinRecyclerView

import com.example.sheettraining.Model.Coin
import com.example.sheettraining.R
import com.example.sheettraining.ViewModel.CoinsListViewModel
import com.example.sheettraining.databinding.FragmentCoinsListBinding
import com.example.sheettraining.databinding.FragmentSigninBinding

class CoinsListFragment : Fragment(), CoinRecyclerView.CoinClickInterface {

    private var _binding: FragmentCoinsListBinding?= null
    private val binding get() = _binding!!
    private lateinit var recyclerView : RecyclerView
    private lateinit var coinsArrayList : ArrayList<Coin>
    private lateinit var myAdapter: CoinRecyclerView

    private lateinit var viewModel: CoinsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCoinsListBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(CoinsListViewModel::class.java)
        val view = binding.root
        recyclerView = binding.rvCoins
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        coinsArrayList = arrayListOf()
        myAdapter = CoinRecyclerView(coinsArrayList, this)

        recyclerView.adapter = myAdapter

        viewModel.coins.observe(viewLifecycleOwner, { list -> list?.let {myAdapter.updateList(it) }} )

        binding.floatbCreate.setOnClickListener{
            findNavController().navigate(R.id.cryproAddFragment)
        }


        return view
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCoinClick(coin: Coin) {
        findNavController().navigate(R.id.detailsCryptoFragment,bundleOf(
            "documentId" to coin.documentId
        )
        )

    }


}