package com.example.glass.domain.usecase

import com.example.glass.data.datasource.DataSource
import com.example.glass.data.datasource.FakeDataSource
import com.example.glass.data.model.Pizza
import com.example.glass.data.model.ResultState

class GetListPizzaImpl : GetListPizza {
    private val dataSource: DataSource = FakeDataSource()
    override suspend fun invoke(): ResultState<List<Pizza>> {
        return ResultState.Success(dataSource.getPizzas())
    }
}