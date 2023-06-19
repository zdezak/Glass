package com.example.glass.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.glass.data.model.ResultState
import com.example.glass.domain.usecase.GetListPizza
import com.example.glass.domain.usecase.GetListPizzaImpl
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel(
) : ViewModel() {

    private val _pizzaUiState = MutableLiveData<PizzaUiState>(PizzaUiState.Loading)
    val pizzaUiState: LiveData<PizzaUiState> = _pizzaUiState
    val getListPizza: GetListPizza = GetListPizzaImpl()

    private var loadingJob: Job? = null

    init {
        loadPizzas()
    }

    private fun loadPizzas() {
        _pizzaUiState.value = PizzaUiState.Loading
        loadingJob?.cancel()
        loadingJob = viewModelScope.launch {
            val newPizzaUiState: PizzaUiState = when (val pizzasResult = getListPizza()) {
                is ResultState.Success -> {
                    PizzaUiState.Success(pizzasResult.data)
                }

                is ResultState.Error -> {
                    pizzasResult.error
                        .takeUnless { it is CancellationException }
                        ?.let(PizzaUiState::Error)
                        ?: PizzaUiState.Loading
                }
            }

            _pizzaUiState.value = (newPizzaUiState)
        }
    }

}