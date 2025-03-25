package com.example.composeapp.repository

import androidx.compose.runtime.mutableStateListOf
import com.example.tfg_aplicacion.models.Food

object FoodRepository {
    val foodList = mutableStateListOf<Food>()

    fun addFood(food: Food) {
        foodList.add(food)
    }

    fun updateFood(index: Int, newFood: Food) {
        foodList[index] = newFood
    }

    fun removeFood(index: Int) {
        foodList.removeAt(index)
    }
}


