package com.example.tfg_aplicacion.models

import androidx.annotation.DrawableRes

//Clase ingrediente
data class Ingredient(
    val name: String,
    @DrawableRes val imageRes: Int
)
