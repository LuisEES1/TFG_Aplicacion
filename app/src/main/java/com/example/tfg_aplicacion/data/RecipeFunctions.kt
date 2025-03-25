package com.example.tfg_aplicacion.data

import com.example.composeapp.repository.FoodRepository
import com.example.tfg_aplicacion.models.Food

// Retorna true si el usuario tiene todos los ingredientes necesarios para la receta
fun canMakeRecipe(recipe: Recipe, userFoods: List<Food>): Boolean {
    // Para cada ingrediente requerido, buscamos si el usuario lo tiene con cantidad > 0
    for (ingredientName in recipe.requiredIngredients) {
        val found = userFoods.any { it.ingredient.name == ingredientName && it.quantity > 0 }
        if (!found) {
            return false
        }
    }
    return true
}
