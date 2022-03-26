package com.arash.altafi.sampleflow1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.arash.altafi.sampleflow1.databinding.ActivityMainBinding
import com.arash.altafi.sampleflow1.model.NetworkResult
import com.arash.altafi.sampleflow1.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var _binding: ActivityMainBinding
    private val pokemonAdapter by lazy {
        PokemonAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        initAdapter()
        fetchAllPokemonResponse()
    }

    private fun initAdapter() {
        _binding.rvPokemon.apply {
            adapter = pokemonAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun fetchAllPokemonResponse() {
        mainViewModel.fetchAllPokemonResponse()
        mainViewModel.responseType.observe(this) { responseType ->
            when (responseType) {
                is NetworkResult.Error -> {
                    _binding.pbPokemon.visibility = View.GONE
                    Toast.makeText(
                        this,
                        responseType.message,
                        Toast.LENGTH_LONG
                    ).show()
                }

                is NetworkResult.Loading -> {
                    _binding.pbPokemon.visibility = View.VISIBLE
                }

                is NetworkResult.Success -> {
                    Log.d("test123321", "fetchAllPokemonResponse: Success !")
                }
            }
        }
        mainViewModel.pokemonList.observe(this) {
            _binding.pbPokemon.visibility = View.GONE
            pokemonAdapter.submitList(it)
        }
    }

}