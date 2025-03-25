package com.example.composeapp.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val title: String, val icon: ImageVector) {
    object Pantry : Screen("Despensa", Icons.Filled.Home)
    object Recipes : Screen("Recetas", Icons.Filled.List)
    object User : Screen("Usuario", Icons.Filled.Person)
}


