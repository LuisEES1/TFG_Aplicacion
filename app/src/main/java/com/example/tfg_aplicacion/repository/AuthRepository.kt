package com.example.tfg_aplicacion.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    //Inicia sesión con email y contraseña
    suspend fun login(email: String, password: String): Result<FirebaseUser?> {
        return try {
            val result = auth
                .signInWithEmailAndPassword(email, password)
                .await()
            Result.success(result.user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    //Crea usuario en Auth, actualiza su displayName,
    //crea documento en Firestore en “users/{uid}”
    //y pre-crea la subcolección “pantry” con un doc _init.
    suspend fun register(username: String, email: String, password: String): Result<FirebaseUser?> {
        return try {
            // 1) Registro en FirebaseAuth
            val result = auth
                .createUserWithEmailAndPassword(email, password)
                .await()
            val user = result.user ?: throw IllegalStateException("Usuario nulo")

            // 2) Actualizar displayName en Auth
            user.updateProfile(
                UserProfileChangeRequest.Builder()
                    .setDisplayName(username)
                    .build()
            ).await()

            // 3) Guardar perfil en Firestore
            val userData = mapOf(
                "userId"    to user.uid,
                "username"  to username,
                "email"     to email,
                "createdAt" to System.currentTimeMillis()
            )
            firestore
                .collection("users")
                .document(user.uid)
                .set(userData)
                .await()

            // 4) Pre-crear la subcolección 'pantry' con un documento inicial
            firestore
                .collection("users")
                .document(user.uid)
                .collection("pantry")
                .document("_init")
                .set(mapOf<String, Any>())  // documento vacío
                .await()

            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    //Cierra sesión en la app
    fun logout() {
        auth.signOut()
    }
}



