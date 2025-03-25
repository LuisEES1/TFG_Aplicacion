package com.example.tfg_aplicacion.data

import androidx.annotation.DrawableRes
import com.example.tfg_aplicacion.R

// Clase que define una receta y sus ingredientes requeridos
data class Recipe(
    val name: String,
    val requiredIngredients: List<String>,
    @DrawableRes val imageRes: Int,
    val instructions: String
)

// Lista de recetas disponibles en la app
val allRecipes = listOf(
    Recipe(
        name = "Macarrones Bolognesa",
        requiredIngredients = listOf("Macarrones", "Carne Picada", "Tomate Frito"),
        imageRes = R.drawable.macarrones_bolognesa, // Asegúrate de tener este recurso
        instructions = "1. Cuece los macarrones. 2. Saltea la carne picada. 3. Agrega el tomate frito y condimenta. 4. Mezcla con la pasta y sirve caliente."
    )
    // Puedes agregar más recetas aquí
)