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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    // Lanzamos un efecto al inicializar
    LaunchedEffect(Unit) {
        FoodRepository.startListening()
    }

    // Colores definidos en tema
    val backgroundColor = ContentBackground    // Fondo de pantalla
    val buttonColor     = Color(0xFFFF9800)    // Color del botón "Agregar"
    val textColor       = IconDark             // Color del texto

    // Estado para controlar el desplegable
    var expanded by remember { mutableStateOf(false) }
    // Guarda el ingrediente seleccionado
    var selectedIngredient by remember { mutableStateOf(availableIngredients.first()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        // Título de la pantalla
        Text(
            text = "Despensa",
            style = MaterialTheme.typography.headlineMedium,
            color = textColor
        )

        Spacer(Modifier.height(16.dp))

        // Desplegable de ingredientes disponibles
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedIngredient.name,
                onValueChange = {},           // Campo de solo lectura
                readOnly = true,
                label = { Text("Selecciona ingrediente", color = textColor) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor     = textColor,
                    focusedBorderColor   = textColor,
                    unfocusedBorderColor = textColor,
                    cursorColor          = textColor,
                    focusedLabelColor    = textColor,
                    unfocusedLabelColor  = textColor
                ),
                trailingIcon = {
                    // Icono para abrir/cerrar el menú
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            // Items del menú con la lista de ingredientes
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                availableIngredients.forEach { ingredient ->
                    DropdownMenuItem(
                        text = { Text(ingredient.name) },
                        onClick = {
                            selectedIngredient = ingredient
                            expanded = false     // Cerramos el menú
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        // Botón para agregar el ingrediente (cantidad fija a 1)
        Button(
            onClick = {
                FoodRepository.addFood(Food(selectedIngredient, 1))
            },
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar", color = Color.White)
        }

        Spacer(Modifier.height(16.dp))

        // Lista de ingredientes añadidos (se sincroniza con Firestore)
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(FoodRepository.foodList) { index, food ->
                FoodCard(
                    food = food,
                    textColor = textColor,
                    onMinus = {
                        // Si al restar la cantidad llega a 0, elimina el documento
                        val newQty = food.quantity - 1
                        if (newQty < 1) FoodRepository.removeFood(index)
                        else FoodRepository.updateFood(index, food.copy(quantity = newQty))
                    },
                    onPlus = {
                        // Incrementa en Firestore
                        FoodRepository.updateFood(index, food.copy(quantity = food.quantity + 1))
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
            // Icono o imagen del ingrediente
            Icon(
                painter = painterResource(id = food.ingredient.imageRes),
                contentDescription = food.ingredient.name,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(64.dp)
                    .padding(8.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                // Nombre y cantidad
                Text(food.ingredient.name, color = textColor)
                Text("Cantidad: ${food.quantity}", color = textColor)
            }
            // Botones para restar y sumar
            IconButton(onClick = onMinus) {
                Icon(Icons.Filled.Delete, contentDescription = "Quitar", tint = textColor)
            }
            IconButton(onClick = onPlus) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar", tint = textColor)
            }
        }
    }
}



