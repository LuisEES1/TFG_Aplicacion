package com.example.tfg_aplicacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeapp.ui.RecipesScreen
import com.example.composeapp.ui.Screen
import com.example.tfg_aplicacion.ui.PantryScreen
import com.example.tfg_aplicacion.ui.UserScreen
import com.example.tfg_aplicacion.ui.LoginScreen
import com.example.tfg_aplicacion.ui.SupermarketsScreen
import com.example.tfg_aplicacion.ui.theme.BottomBarLight
import com.example.tfg_aplicacion.ui.theme.IconDark
import com.example.tfg_aplicacion.ui.theme.TopBarOrange
import com.example.tfg_aplicacion.viewmodel.AuthUiState
import com.example.tfg_aplicacion.viewmodel.AuthViewModel

/**
 * MainActivity es el entry-point de la aplicación.
 * Contiene la función RootApp que gestiona la navegación
 * entre Login y las pantallas principales una vez autenticado.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContent define el contenido Compose de esta Activity
        setContent {
            RootApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootApp() {
    // Obtenemos el AuthViewModel para manejar el estado de autenticación
    val authViewModel: AuthViewModel = viewModel()
    // 'uiState' refleja si estamos logueados o no
    val uiState by authViewModel.uiState.collectAsState()

    // Si no hay usuario autenticado, mostramos la pantalla de login
    if (uiState !is AuthUiState.Success) {
        LoginScreen(
            authViewModel = authViewModel,
            onAuthSuccess = {
            }
        )
    } else {
        // Usuario autenticado: configuramos navegación con BottomBar
        // currentScreen guarda la pestaña seleccionada
        var currentScreen: Screen by remember { mutableStateOf(Screen.Pantry) }

        Scaffold(
            // Barra superior con título dinámico según la pestaña
            topBar = {
                TopAppBar(
                    title = { Text(currentScreen.title) },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = TopBarOrange
                    )
                )
            },
            // Barra inferior de navegación entre pantallas
            bottomBar = {
                NavigationBar(containerColor = BottomBarLight) {
                    listOf(
                        Screen.Pantry,
                        Screen.Recipes,
                        Screen.Supermarket,
                        Screen.User
                    ).forEach { screen ->
                        NavigationBarItem(
                            selected = screen == currentScreen,
                            onClick = { currentScreen = screen },
                            icon = {
                                Icon(
                                    imageVector = screen.icon,
                                    contentDescription = screen.title,
                                    // Cambio de color si está seleccionado
                                    tint = if (screen == currentScreen) TopBarOrange else IconDark
                                )
                            },
                            label = { Text(screen.title) }
                        )
                    }
                }
            }
        ) { innerPadding ->
            // Contenido de la pantalla según la pestaña activa
            when (currentScreen) {
                Screen.Pantry  -> PantryScreen(Modifier.padding(innerPadding))
                Screen.Recipes -> RecipesScreen(Modifier.padding(innerPadding))
                Screen.Supermarket -> SupermarketsScreen(Modifier.padding(innerPadding))
                Screen.User    -> UserScreen(
                    modifier = Modifier.padding(innerPadding),
                    // Al cerrar sesión, invocamos ViewModel.logout()
                    onLogout = { authViewModel.logout() }
                )
            }
        }
    }
}

