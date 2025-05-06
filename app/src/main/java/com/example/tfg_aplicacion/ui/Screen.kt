package com.example.composeapp.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

//Controlador nav de las pantallas de la app
sealed class Screen(val title: String, val icon: ImageVector) {
    object Pantry : Screen("Despensa", Icons.Filled.Home)
    object Recipes : Screen("Recetas", Icons.Filled.List)
    object Supermarket : Screen("Compras", Icons.Filled.AddShoppingCart)
    object User : Screen("Usuario", Icons.Filled.Person)
}


