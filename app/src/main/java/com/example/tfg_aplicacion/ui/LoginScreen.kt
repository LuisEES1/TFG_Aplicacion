package com.example.tfg_aplicacion.ui

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tfg_aplicacion.R
import com.example.tfg_aplicacion.viewmodel.AuthUiState
import com.example.tfg_aplicacion.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel = viewModel(),
    onAuthSuccess: () -> Unit
) {
    // Observa el estado de autenticación
    val uiState by authViewModel.uiState.collectAsState()
    // Indica si estamos en modo registro o login
    val isRegistering by authViewModel.isRegistering.collectAsState()

    // Campos del formulario y estado de error local
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var formError by remember { mutableStateOf<String?>(null) }

    // Navega a la siguiente pantalla si la autenticación fue bien
    if (uiState is AuthUiState.Success) {
        LaunchedEffect(Unit) {
            onAuthSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        // Logo de la app en la parte superior
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo de la App",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 32.dp)
        )

        // Mensaje de error de validación o de Firebase
        formError?.let { msg ->
            Text(
                text = msg,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
        }
        if (uiState is AuthUiState.Error) {
            Text(
                text = (uiState as AuthUiState.Error).message,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
        }

        // Título dinámico: registro o login
        Text(
            text = if (isRegistering) "Registro" else "Iniciar Sesión",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Formulario de registro (incluye nombre de usuario y confirmación de contraseña)
        if (isRegistering) {
            OutlinedTextField(
                value = username,
                onValueChange = { username = it; formError = null },
                label = { Text("Nombre de usuario") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
        }

        // Campo email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it; formError = null },
            label = { Text("Correo electrónico") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        // Campo contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it; formError = null },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        // Campo de confirmar contraseña solo en registro
        if (isRegistering) {
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it; formError = null },
                label = { Text("Confirmar contraseña") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(Modifier.height(24.dp))

        // Botón principal: valida datos antes de llamar a ViewModel
        Button(
            onClick = {
                // Validaciones básicas antes de enviar
                formError = when {
                    isRegistering && username.isBlank() -> "El nombre de usuario no puede estar vacío"
                    email.isBlank() -> "El correo no puede estar vacío"
                    !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Introduce un correo válido"
                    password.isBlank() -> "La contraseña no puede estar vacía"
                    isRegistering && password != confirmPassword -> "Las contraseñas no coinciden"
                    else -> null
                }
                // Si no hay errores, invoca al ViewModel
                if (formError == null) {
                    if (isRegistering) authViewModel.register(username.trim(), email.trim(), password)
                    else             authViewModel.login(email.trim(), password)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(
                text = if (isRegistering) "Registrarse" else "Entrar",
                fontSize = 16.sp
            )
        }

        Spacer(Modifier.height(16.dp))

        // Texto que alterna entre login y registro
        val toggleText = buildAnnotatedString {
            append(if (isRegistering) "¿Ya tienes cuenta? " else "¿No tienes cuenta? ")
            pushStringAnnotation("TOGGLE", "toggle")
            withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)) {
                append(if (isRegistering) "Inicia sesión" else "Regístrate")
            }
            pop()
        }
        ClickableText(
            text = toggleText,
            style = LocalTextStyle.current.copy(fontSize = 14.sp),
            onClick = { pos ->
                toggleText.getStringAnnotations("TOGGLE", pos, pos)
                    .firstOrNull()?.let { authViewModel.toggleRegistering() }
            }
        )

        Spacer(Modifier.height(16.dp))

        // Indicador de carga mientras se procesa la petición
        if (uiState is AuthUiState.Loading) {
            CircularProgressIndicator()
        }
    }
}






