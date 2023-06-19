package com.example.glass.ui.home

import com.example.glass.data.model.Pizza

sealed class PizzaUiState {
    data class Success(val pizzas: List<Pizza>) : PizzaUiState()
    data class Error(val exception: Throwable) : PizzaUiState()
    object Loading : PizzaUiState()
}