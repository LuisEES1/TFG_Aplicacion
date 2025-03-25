package com.example.tfg_aplicacion.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composeapp.repository.FoodRepository
import com.example.tfg_aplicacion.data.availableIngredients
import com.example.tfg_aplicacion.models.Food
import com.example.tfg_aplicacion.ui.theme.ContentBackground
import com.example.tfg_aplicacion.ui.theme.IconDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantryScreen(modifier: Modifier = Modifier) {
    // Mantenemos el fondo blanco
    val backgroundColor = ContentBackground
    val buttonColor = Color(0xFFFF9800) // Podrías reutilizar TopBarOrange
    val textColor = IconDark           // Texto oscuro

    // Control del desplegable
    var expanded by remember { mutableStateOf(false) }
    var selectedIngredient by remember { mutableStateOf(availableIngredients.first()) }
    var quantity by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        Text("Despensa", style = MaterialTheme.typography.headlineMedium, color = textColor)

        Spacer(modifier = Modifier.height(16.dp))

        // Desplegable
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            OutlinedTextField(
                value = selectedIngredient.name,
                onValueChange = {},
                readOnly = true,
                label = { Text("Selecciona ingrediente", color = textColor) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = textColor,
                    focusedBorderColor = textColor,
                    unfocusedBorderColor = textColor,
                    cursorColor = textColor,
                    focusedLabelColor = textColor,
                    unfocusedLabelColor = textColor
                ),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                availableIngredients.forEach { ingredient ->
                    DropdownMenuItem(
                        text = { Text(ingredient.name) },
                        onClick = {
                            selectedIngredient = ingredient
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Cantidad
        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Cantidad", color = textColor) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedTextColor = textColor,
                focusedBorderColor = textColor,
                unfocusedBorderColor = textColor,
                cursorColor = textColor,
                focusedLabelColor = textColor,
                unfocusedLabelColor = textColor
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Botón Agregar
        Button(
            onClick = {
                val qty = quantity.toIntOrNull() ?: 1
                FoodRepository.addFood(Food(selectedIngredient, qty))
                quantity = ""
            },
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
        ) {
            Text("Agregar", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(FoodRepository.foodList) { index, food ->
                FoodCard(
                    food = food,
                    textColor = textColor,
                    onMinus = {
                        val newQty = food.quantity - 1
                        if (newQty < 1) {
                            FoodRepository.removeFood(index)
                        } else {
                            FoodRepository.updateFood(index, food.copy(quantity = newQty))
                        }
                    },
                    onPlus = {
                        val newQty = food.quantity + 1
                        FoodRepository.updateFood(index, food.copy(quantity = newQty))
                    }
                )
            }
        }
    }
}

@Composable
fun FoodCard(
    food: Food,
    textColor: Color,
    onMinus: () -> Unit,
    onPlus: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = food.ingredient.imageRes),
                contentDescription = food.ingredient.name,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(64.dp)
                    .padding(8.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(food.ingredient.name, color = textColor)
                Text("Cantidad: ${food.quantity}", color = textColor)
            }

            IconButton(onClick = onMinus) {
                Icon(Icons.Filled.Delete, contentDescription = "Quitar", tint = textColor)
            }
            IconButton(onClick = onPlus) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar", tint = textColor)
            }
        }
    }
}
