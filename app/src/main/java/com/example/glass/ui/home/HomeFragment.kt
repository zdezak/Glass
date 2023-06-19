package com.example.glass.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.glass.databinding.FragmentHomeBinding
import com.livermor.delegateadapter.delegate.CompositeDelegateAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeViewModel.pizzaUiState.observe(viewLifecycleOwner) { value ->
            when (value) {
                is PizzaUiState.Loading -> {

                }

                is PizzaUiState.Success -> {
                    val adapter = CompositeDelegateAdapter(
                        PizzaDelegateAdapter()
                    )
                    adapter.swapData(value.pizzas)
                    binding.recyclerView.layoutManager =
                        LinearLayoutManager(binding.root.context)
                    binding.recyclerView.adapter = adapter
                }

                is PizzaUiState.Error -> {

                }
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}