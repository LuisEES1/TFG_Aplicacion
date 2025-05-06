package com.example.composeapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composeapp.repository.FoodRepository
import com.example.tfg_aplicacion.data.*
import com.example.tfg_aplicacion.ui.theme.ContentBackground
import com.example.tfg_aplicacion.ui.theme.IconDark

@Composable
fun RecipesScreen(modifier: Modifier = Modifier) {
    // Lista actual de ingredientes del usuario
    val userFoods = FoodRepository.foodList

    // Filtramos sólo aquellas recetas para las que tenemos todos los ingredientes
    val possibleRecipes = allRecipes.filter { canMakeRecipe(it, userFoods) }

    // Definimos el orden de las categorías
    val categories = listOf(
        RecipeCategory.DESAYUNO,
        RecipeCategory.ENTRANTE,
        RecipeCategory.PRINCIPAL,
        RecipeCategory.POSTRE
    )

    // Estado de desplegado para cada categoría
    val expandedStates = remember {
        mutableStateMapOf<RecipeCategory, Boolean>()
            .apply { categories.forEach { this[it] = false } }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ContentBackground)
            .padding(16.dp)
    ) {
        // Si no hay recetas disponibles, mostramos un mensaje
        if (possibleRecipes.isEmpty()) {
            item {
                Text(
                    text = "No hay recetas disponibles con tus ingredientes actuales.",
                    color = IconDark,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                )
            }
            return@LazyColumn
        }

        // Recorremos cada categoría en orden
        categories.forEach { category ->
            // Recetas de esta categoría que el usuario puede preparar
            val recs = possibleRecipes.filter { it.category == category }
            if (recs.isEmpty()) return@forEach

            // Header desplegable de la categoría
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable {
                            // Alternamos expandido/plegado
                            expandedStates[category] = !(expandedStates[category] ?: false)
                        },
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        Text(
                            text = category.displayName,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            imageVector = if (expandedStates[category] == true)
                                Icons.Filled.KeyboardArrowUp
                            else
                                Icons.Filled.KeyboardArrowDown,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }

            // Si está expandida, mostramos cada receta
            if (expandedStates[category] == true) {
                items(recs) { recipe ->
                    RecipeCard(recipe)
                }
            }
        }
    }
}

@Composable
private fun RecipeCard(recipe: Recipe) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            // Imagen de la receta
            Image(
                painter = painterResource(id = recipe.imageRes),
                contentDescription = recipe.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(16.dp)) {
                // Nombre
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.headlineSmall,
                    color = IconDark
                )
                Spacer(Modifier.height(8.dp))

                // Ingredientes
                Text(
                    text = "Ingredientes:",
                    style = MaterialTheme.typography.titleSmall,
                    color = IconDark
                )
                recipe.requiredIngredients.forEach { ing ->
                    Text(
                        text = "• $ing",
                        style = MaterialTheme.typography.bodyMedium,
                        color = IconDark,
                        modifier = Modifier.padding(start = 8.dp, top = 2.dp)
                    )
                }
                Spacer(Modifier.height(8.dp))

                // Pasos
                Text(
                    text = "Preparación:",
                    style = MaterialTheme.typography.titleSmall,
                    color = IconDark
                )
                // Separamos los pasos por números seguidos de punto
                val steps = recipe.instructions
                    .split(Regex("\\d+\\."))
                    .map { it.trim() }
                    .filter { it.isNotEmpty() }
                steps.forEachIndexed { i, step ->
                    Text(
                        text = "${i + 1}. $step",
                        style = MaterialTheme.typography.bodyMedium,
                        color = IconDark,
                        modifier = Modifier.padding(start = 8.dp, top = 2.dp)
                    )
                }
            }
        }
    }
}

// Extensión para capitalizar el nombre de la categoría
private val RecipeCategory.displayName: String
    get() = name.lowercase().replaceFirstChar { it.uppercase() }
