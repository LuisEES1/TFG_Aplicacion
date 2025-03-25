package com.example.composeapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tfg_aplicacion.R

@Composable
fun UserScreen(modifier: Modifier = Modifier) {
    // Datos ficticios de usuario
    val userProfile = UserProfile(
        avatarRes = R.drawable.ic_launcher_foreground, // tu drawable
        name = "Usuario",
        email = "usuario1@gmail.com",
        code = "ID: 168128273"
    )

    // Lista de secciones
    val userMenuItems = listOf(
        UserMenuItem("Mis trofeos", R.drawable.ic_launcher_foreground),
        UserMenuItem("Mis bonificaciones", R.drawable.ic_launcher_foreground),
        UserMenuItem("Lista de deseos", R.drawable.ic_launcher_foreground),
        UserMenuItem("Asistencia", R.drawable.ic_launcher_foreground),
        UserMenuItem("Ajustes", R.drawable.ic_launcher_foreground)
    )

    // Ajusta colores y estilo a tu gusto
    val backgroundColor = Color.White
    val titleColor = Color(0xFF333333)    // Texto oscuro
    val cardColor = Color(0xFFF2F2F2)     // Fondo gris claro para la tarjeta
    val iconColor = Color(0xFF666666)     // Iconos suaves
    val dividerColor = Color(0xFFE0E0E0)  // Divider gris claro

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        // Título principal
        Text(
            text = "Mi CUENTA",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            color = titleColor,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        // Cabecera con info de usuario
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .wrapContentHeight(),
            colors = CardDefaults.cardColors(containerColor = cardColor)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Avatar
                Image(
                    painter = painterResource(userProfile.avatarRes),
                    contentDescription = "Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(MaterialTheme.shapes.medium)
                )
                Spacer(modifier = Modifier.width(16.dp))

                // Nombre, email y código
                Column {
                    Text(
                        text = userProfile.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = titleColor
                    )
                    Text(
                        text = userProfile.email,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = iconColor
                    )
                    Text(
                        text = "Tu código de identificación\n(${userProfile.code})",
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        color = iconColor
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Lista de secciones
        LazyColumn {
            items(userMenuItems) { item ->
                UserMenuRow(
                    menuItem = item,
                    iconColor = iconColor,
                    dividerColor = dividerColor
                )
            }
        }
    }
}

// Fila de cada ítem del menú
@Composable
fun UserMenuRow(
    menuItem: UserMenuItem,
    iconColor: Color,
    dividerColor: Color
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icono
            Icon(
                painter = painterResource(menuItem.iconRes),
                contentDescription = menuItem.title,
                tint = iconColor,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Título
            Text(
                text = menuItem.title,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                color = Color.Black
            )

            Spacer(modifier = Modifier.weight(1f))

            // Texto extra si lo hay (ej: "PLUS +")
            if (menuItem.trailingText != null) {
                Text(
                    text = menuItem.trailingText,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    color = Color.Red // Destacar en rojo, por ejemplo
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            // Flecha de "navegación" (opcional)
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = "Ir",
                tint = iconColor,
                modifier = Modifier.size(16.dp)
            )
        }

        // Divider
        Divider(color = dividerColor, thickness = 1.dp)
    }
}

// Clases de datos para el usuario y el menú
data class UserProfile(
    val avatarRes: Int,
    val name: String,
    val email: String,
    val code: String
)

data class UserMenuItem(
    val title: String,
    val iconRes: Int,
    val trailingText: String? = null
)



