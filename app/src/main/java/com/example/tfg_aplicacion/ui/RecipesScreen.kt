package com.example.composeapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tfg_aplicacion.ui.theme.ContentBackground
import com.example.tfg_aplicacion.ui.theme.IconDark
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.composeapp.repository.FoodRepository
import com.example.tfg_aplicacion.data.Recipe
import com.example.tfg_aplicacion.data.allRecipes
import com.example.tfg_aplicacion.data.canMakeRecipe

@Composable
fun RecipesScreen(modifier: Modifier = Modifier) {
    // Lista de ingredientes que el usuario tiene
    val userFoods = FoodRepository.foodList

    // Filtramos recetas que se puedan preparar
    val possibleRecipes = allRecipes.filter { recipe ->
        canMakeRecipe(recipe, userFoods)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ContentBackground)
            .padding(16.dp)
    ) {
        Text(
            text = "Recetas que puedes preparar:",
            style = MaterialTheme.typography.headlineMedium,
            color = IconDark
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (possibleRecipes.isEmpty()) {
            Text(
                text = "No hay recetas disponibles con tus ingredientes actuales.",
                color = IconDark
            )
        } else {
            LazyColumn {
                items(possibleRecipes) { recipe ->
                    RecipeCard(recipe = recipe)
                }
            }
        }
    }
}


@Composable
fun RecipeCard(recipe: Recipe) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column {
            // Imagen grande ocupando el ancho
            Image(
                painter = painterResource(id = recipe.imageRes),
                contentDescription = recipe.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            // Contenido textual: nombre y cómo hacer la receta
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = recipe.instructions,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}



