package com.example.composeapp.repository

import androidx.compose.runtime.mutableStateListOf
import com.example.tfg_aplicacion.models.Food
import com.example.tfg_aplicacion.models.Ingredient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

object FoodRepository {
    // Estado que refleja la sub-colección pantry
    val foodList = mutableStateListOf<Food>()

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    //llamada una vez al arrancar la pantalla de despensa para mantener foodList sincronizada
    fun startListening() {
        val uid = auth.currentUser?.uid ?: return
        firestore.collection("users")
            .document(uid)
            .collection("pantry")
            .addSnapshotListener { snap, err ->
                if (err != null) return@addSnapshotListener
                if (snap != null) {
                    // Reconstruimos la lista entera
                    foodList.clear()
                    for (doc in snap.documents) {
                        // Saltamos el documento inicial "_init" si lo creaste
                        if (doc.id == "_init") continue
                        val name     = doc.getString("name") ?: continue
                        val imageRes = doc.getLong("imageRes")?.toInt() ?: continue
                        val qty      = doc.getLong("quantity")?.toInt() ?: continue
                        val ingredient = Ingredient(name, imageRes)
                        foodList.add(Food(ingredient, qty))
                    }
                }
            }
    }

    //ñade un nuevo ingrediente con qty=1 o lo actualiza si ya existe
    fun addFood(food: Food) {
        val uid = auth.currentUser?.uid ?: return
        val docId = food.ingredient.name
            .lowercase()
            .replace(" ", "_")
        val data = mapOf(
            "name"     to food.ingredient.name,
            "imageRes" to food.ingredient.imageRes,
            "quantity" to food.quantity  // aquí, normalmente 1
        )
        firestore.collection("users")
            .document(uid)
            .collection("pantry")
            .document(docId)
            .set(data, SetOptions.merge())
    }

    //Actualiza la cantidad en Firestore
    fun updateFood(index: Int, newFood: Food) {
        val uid = auth.currentUser?.uid ?: return
        val docId = newFood.ingredient.name
            .lowercase()
            .replace(" ", "_")
        firestore.collection("users")
            .document(uid)
            .collection("pantry")
            .document(docId)
            .update("quantity", newFood.quantity)
    }

    //Borra el documento del ingrediente si quantity<1
    fun removeFood(index: Int) {
        val food = foodList.getOrNull(index) ?: return
        val uid = auth.currentUser?.uid ?: return
        val docId = food.ingredient.name
            .lowercase()
            .replace(" ", "_")
        firestore.collection("users")
            .document(uid)
            .collection("pantry")
            .document(docId)
            .delete()
    }
}



