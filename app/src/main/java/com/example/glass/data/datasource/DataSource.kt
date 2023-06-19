package com.example.glass.data.datasource

import com.example.glass.data.model.Pizza

interface DataSource {
    fun getPizzas(): List<Pizza>
}