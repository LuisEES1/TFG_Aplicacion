package com.example.tfg_aplicacion.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue                // Import necesario para 'by'
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tfg_aplicacion.R
import com.example.tfg_aplicacion.viewmodel.AuthUiState
import com.example.tfg_aplicacion.viewmodel.AuthViewModel

@Composable
fun UserScreen(
    modifier: Modifier = Modifier,
    onLogout: () -> Unit               // Callback para cerrar sesión
) {
    // Obtenemos el ViewModel de autenticación
    val authViewModel: AuthViewModel = viewModel()
    // Observamos el estado de autenticación
    val uiState by authViewModel.uiState.collectAsState()

    // Extraemos el usuario logueado
    val user = (uiState as? AuthUiState.Success)?.user

    // Colores para la UI
    val backgroundColor = Color.White    // Fondo de la pantalla
    val titleColor      = Color(0xFF333333)  // Texto principal
    val cardColor       = Color(0xFFF2F2F2)  // Fondo de la tarjeta
    val iconColor       = Color(0xFF666666)  // Iconos y texto secundario
    val dividerColor    = Color(0xFFE0E0E0)  // Separadores

    Column(
        modifier = modifier
            .fillMaxSize()                // Ocupa toda la pantalla
            .background(backgroundColor)  // Aplica el fondo blanco
    ) {
        // Título de la sección
        Text(
            text = "Mi CUENTA",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            color = titleColor,
            modifier = Modifier.padding(16.dp)
        )

        // Tarjeta con info del usuario
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = CardDefaults.cardColors(containerColor = cardColor),
            shape = MaterialTheme.shapes.medium
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                // Avatar del usuario (placeholder)
                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(MaterialTheme.shapes.medium)
                )

                Spacer(Modifier.width(16.dp))  // Espacio entre avatar y texto

                Column {
                    // Nombre de usuario (displayName de Firebase)
                    Text(
                        text = user?.displayName ?: "Usuario",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = titleColor
                    )
                    Spacer(Modifier.height(4.dp))
                    // Email del usuario
                    Text(
                        text = user?.email ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        color = iconColor
                    )
                }
            }
        }

        Spacer(Modifier.height(24.dp))  // Separador

        // Botón para cerrar sesión
        Button(
            onClick = onLogout, // Invoca callback
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7043)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .height(48.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = "Cerrar sesión",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(Modifier.height(24.dp))

        // Opciones de menú adicionales
        val menuItems = listOf(
            "Mis bonificaciones",
            "Asistencia",
            "Ajustes"
        )
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(menuItems) { title ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /* Aquí podrías navegar a otra pantalla */ }
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    // Icono genérico para cada sección
                    Icon(
                        painter = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = null,
                        tint = iconColor,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.width(16.dp))
                    // Título de la opción
                    Text(text = title, color = titleColor)
                    Spacer(Modifier.weight(1f))
                    // Flecha indicadora de navegación
                    Icon(
                        imageVector = Icons.Filled.ChevronRight,
                        contentDescription = null,
                        tint = iconColor
                    )
                }
                // Línea divisoria entre elementos
                Divider(color = dividerColor, thickness = 1.dp)
            }
        }
    }
}


