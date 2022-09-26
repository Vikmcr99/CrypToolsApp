package com.example.sheettraining.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sheettraining.Dao.AuthDao.Companion.auth
import com.example.sheettraining.R
import com.example.sheettraining.ViewModel.HomeViewModel
import com.example.sheettraining.databinding.FragmentHome2Binding


class HomeFragment : Fragment() {

    private var _binding: FragmentHome2Binding?= null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHome2Binding.inflate(inflater,container,false)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.tvSair.setOnClickListener{
            auth.signOut()
            findNavController().popBackStack(R.id.signinFragment, true)
            findNavController().navigate(R.id.signinFragment)
        }

        binding.tvInfoCoins.setOnClickListener{
            findNavController().navigate(R.id.cryptoInfoFragment)
        }

        binding.tvRegistros.setOnClickListener{
            findNavController().navigate(R.id.coinsListFragment)
        }
        return binding.root
    }

}