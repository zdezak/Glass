package com.example.glass.domain.usecase

import com.example.glass.data.model.Pizza
import com.example.glass.data.model.ResultState

interface GetListPizza {
    suspend operator fun invoke(): ResultState<List<Pizza>>
}