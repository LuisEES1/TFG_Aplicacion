package com.example.tfg_aplicacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.composeapp.ui.RecipesScreen
import com.example.composeapp.ui.Screen
import com.example.composeapp.ui.UserScreen
import com.example.tfg_aplicacion.ui.PantryScreen
import com.example.tfg_aplicacion.ui.theme.BottomBarLight
import com.example.tfg_aplicacion.ui.theme.IconDark
import com.example.tfg_aplicacion.ui.theme.TopBarOrange

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MyApp() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    // Control de la pantalla actual
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Pantry) }

    Scaffold(
        // TopAppBar
        topBar = {
            TopAppBar(
                title = { Text(currentScreen.title, color = MaterialTheme.colorScheme.onPrimary) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = TopBarOrange,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },

        // Bottom Navigation
        bottomBar = {
            NavigationBar(containerColor = BottomBarLight) {
                listOf(Screen.Pantry, Screen.Recipes, Screen.User).forEach { screen ->
                    NavigationBarItem(
                        selected = (screen == currentScreen),
                        onClick = { currentScreen = screen },
                        icon = {
                            Icon(
                                screen.icon,
                                contentDescription = screen.title,
                                tint = if (screen == currentScreen) TopBarOrange else IconDark
                            )
                        },
                        label = {
                            Text(
                                screen.title,
                                color = if (screen == currentScreen) TopBarOrange else IconDark
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        // Contenido según la pantalla
        when (currentScreen) {
            Screen.Pantry -> PantryScreen(Modifier.padding(innerPadding))
            Screen.Recipes -> RecipesScreen(Modifier.padding(innerPadding))
            Screen.User -> UserScreen(Modifier.padding(innerPadding))
        }
    }
}



