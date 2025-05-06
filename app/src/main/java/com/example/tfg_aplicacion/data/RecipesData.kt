package com.example.tfg_aplicacion.data

import androidx.annotation.DrawableRes
import com.example.tfg_aplicacion.R

/**
 * Categorías de recetas: desayuno, entrante, plato principal o postre.
 */
enum class RecipeCategory {
    DESAYUNO,
    ENTRANTE,
    PRINCIPAL,
    POSTRE
}

/**
 * Clase que define una receta y sus ingredientes requeridos,
 * junto con su categoría.
 */
data class Recipe(
    val name: String,
    val requiredIngredients: List<String>,
    @DrawableRes val imageRes: Int,
    val instructions: String,
    val category: RecipeCategory
)

// Lista de recetas disponibles con su categoría correspondiente
val allRecipes = listOf(
    Recipe(
        name = "Macarrones Bolognesa",
        requiredIngredients = listOf("Macarrones", "Carne Picada", "Tomate Frito"),
        imageRes = R.drawable.macarrones_bolognesa,
        instructions = "1. Cuece los macarrones. 2. Saltea la carne picada. 3. Agrega el tomate frito y condimenta. 4. Mezcla con la pasta y sirve caliente.",
        category = RecipeCategory.PRINCIPAL
    ),
    Recipe(
        name = "Tortilla de Patatas",
        requiredIngredients = listOf("Patatas", "Huevos", "Cebolla", "Aceite de Oliva", "Sal"),
        imageRes = R.drawable.tortilla_patatas,
        instructions = "1. Pela y corta las patatas y la cebolla. 2. Fríelas en aceite de oliva. 3. Bate los huevos con sal, añade las patatas y cebolla. 4. Cuaja la mezcla por ambos lados y sirve.",
        category = RecipeCategory.PRINCIPAL
    ),
    Recipe(
        name = "Ensalada Mixta",
        requiredIngredients = listOf("Lechuga", "Tomates Frescos", "Pepino", "Maíz", "Aceite de Oliva", "Sal", "Vinagre"),
        imageRes = R.drawable.ensalada_mixta,
        instructions = "1. Lava y trocea la lechuga, tomates y pepino. 2. Mezcla en un bol con maíz. 3. Aliña con aceite, vinagre y sal al gusto.",
        category = RecipeCategory.ENTRANTE
    ),
    Recipe(
        name = "Huevos Revueltos con Bacon",
        requiredIngredients = listOf("Huevos", "Bacon", "Mantequilla", "Sal", "Pimienta"),
        imageRes = R.drawable.huevos_bacon,
        instructions = "1. Corta el bacon en tiras y fríelo. 2. Bate los huevos con sal y pimienta. 3. Añade mantequilla, vierte los huevos y remueve hasta cuajar.",
        category = RecipeCategory.DESAYUNO
    ),
    Recipe(
        name = "Pollo al Horno",
        requiredIngredients = listOf("Pollo", "Ajo", "Pimienta", "Aceite de Oliva", "Sal"),
        imageRes = R.drawable.pollo_horno,
        instructions = "1. Marina el pollo con ajo picado, aceite, sal y pimienta. 2. Hornea a 180°C durante 45 minutos. 3. Sirve acompañado de verduras.",
        category = RecipeCategory.PRINCIPAL
    ),
    Recipe(
        name = "Puré de Patatas",
        requiredIngredients = listOf("Patatas", "Leche", "Mantequilla", "Sal"),
        imageRes = R.drawable.pure_patatas,
        instructions = "1. Cuece las patatas peladas en agua con sal. 2. Escúrrelas y haz puré. 3. Añade mantequilla y leche caliente, mezcla hasta textura suave.",
        category = RecipeCategory.ENTRANTE
    ),
    Recipe(
        name = "Arroz con Verduras",
        requiredIngredients = listOf("Arroz", "Zanahorias", "Guisantes", "Maíz", "Aceite de Oliva", "Sal"),
        imageRes = R.drawable.arroz_verduras,
        instructions = "1. Sofríe zanahorias picadas en aceite. 2. Añade arroz, agua y sal. 3. Cuando falten 5 minutos, incorpora guisantes y maíz. 4. Cocina hasta absorber el líquido.",
        category = RecipeCategory.PRINCIPAL
    ),
    Recipe(
        name = "Bocadillo de Atún",
        requiredIngredients = listOf("Pan", "Atún", "Lechuga", "Tomates Frescos", "Aceite de Oliva", "Sal"),
        imageRes = R.drawable.bocadillo_atun,
        instructions = "1. Abre el pan por la mitad. 2. Coloca atún escurrido, lechuga y tomate. 3. Aliña con aceite y sal. 4. Cierra y sirve.",
        category = RecipeCategory.DESAYUNO
    ),
    Recipe(
        name = "Pizza Casera",
        requiredIngredients = listOf("Harina", "Levadura", "Agua", "Tomate Frito", "Mozzarella", "Champiñones", "Bacon"),
        imageRes = R.drawable.pizza_casera,
        instructions = "1. Prepara la masa con harina, levadura y agua. 2. Extiende y cubre con tomate frito. 3. Añade mozzarella, champiñones y bacon. 4. Hornea a 220°C durante 12-15 minutos.",
        category = RecipeCategory.PRINCIPAL
    ),
    Recipe(
        name = "Tortilla de Champiñones",
        requiredIngredients = listOf("Huevos", "Champiñones", "Cebolla", "Aceite de Oliva", "Sal"),
        imageRes = R.drawable.tortilla_champi_ones,
        instructions = "1. Saltea cebolla y champiñones. 2. Bate huevos con sal y vierte sobre verduras. 3. Cocina a fuego medio hasta cuajar ambos lados.",
        category = RecipeCategory.DESAYUNO
    ),
    Recipe(
        name = "Espaguetis a la Carbonara",
        requiredIngredients = listOf("Spaguetti", "Bacon", "Huevos", "Queso", "Pimienta", "Sal"),
        imageRes = R.drawable.espaguetis_carbonara,
        instructions = "1. Cuece los espaguetis. 2. Fríe el bacon. 3. Bate huevos con queso, pimienta y sal. 4. Mezcla la pasta caliente con la mezcla y el bacon.",
        category = RecipeCategory.PRINCIPAL
    ),
    Recipe(
        name = "Macarrones con Queso",
        requiredIngredients = listOf("Macarrones", "Queso", "Leche", "Mantequilla", "Harina", "Sal"),
        imageRes = R.drawable.macarrones_queso,
        instructions = "1. Cuece los macarrones. 2. Prepara bechamel con mantequilla, harina y leche. 3. Añade queso a la bechamel. 4. Mezcla con la pasta y hornea si deseas.",
        category = RecipeCategory.PRINCIPAL
    ),
    Recipe(
        name = "Ensalada de Espinacas y Fresas",
        requiredIngredients = listOf("Espinacas", "Fresas", "Nuez", "Queso", "Aceite de Oliva", "Vinagre", "Sal"),
        imageRes = R.drawable.ensalada_espinacas_fresas,
        instructions = "1. Lava espinacas y fresas. 2. Corta fresas y mezcla con espinacas. 3. Añade nueces y queso. 4. Aliña con aceite, vinagre y sal.",
        category = RecipeCategory.ENTRANTE
    ),
    Recipe(
        name = "Crema de Calabacín",
        requiredIngredients = listOf("Calabacín", "Cebolla", "Crema de Leche", "Aceite de Oliva", "Sal", "Pimienta"),
        imageRes = R.drawable.crema_calabacin,
        instructions = "1. Sofríe cebolla y calabacín. 2. Añade agua y cocina. 3. Tritura y añade crema de leche. 4. Calienta sin hervir, salpimienta y sirve.",
        category = RecipeCategory.ENTRANTE
    ),
    Recipe(
        name = "Berenjenas Rellenas",
        requiredIngredients = listOf("Berenjena", "Carne Picada", "Tomate Frito", "Queso", "Aceite de Oliva", "Sal", "Pimienta"),
        imageRes = R.drawable.berenjenas_rellenas,
        instructions = "1. Parte berenjenas y vacía. 2. Saltea carne con relleno y tomate frito, salpimienta. 3. Rellena berenjenas y cubre con queso. 4. Hornea a 180°C 20 min.",
        category = RecipeCategory.PRINCIPAL
    ),
    Recipe(
        name = "Guiso de Chorizo y Patata Dulce",
        requiredIngredients = listOf("Chorizo", "Patatas", "Cebolla", "Ajo", "Aceite de Oliva", "Sal"),
        imageRes = R.drawable.guiso_chorizo_patatadulce,
        instructions = "1. Sofríe cebolla y ajo. 2. Añade chorizo en rodajas y patata dulce. 3. Cubre con agua y cocina hasta tierna. 4. Salpimienta y sirve.",
        category = RecipeCategory.PRINCIPAL
    ),
    Recipe(
        name = "Fusilli con Atún y Brócoli",
        requiredIngredients = listOf("Fusilli", "Atún", "Brócoli", "Ajo", "Aceite de Oliva", "Sal"),
        imageRes = R.drawable.fusilli_atun_brocoli,
        instructions = "1. Cuece fusilli y brócoli juntos. 2. Fríe ajo en aceite. 3. Añade atún escurrido. 4. Mezcla con la pasta y sirve.",
        category = RecipeCategory.PRINCIPAL
    ),
    Recipe(
        name = "Puré de Zanahoria y Naranja",
        requiredIngredients = listOf("Zanahorias", "Naranja", "Mantequilla", "Sal"),
        imageRes = R.drawable.pure_zanahoria_naranja,
        instructions = "1. Cuece zanahorias. 2. Tritura con mantequilla y zumo de naranja. 3. Sal al gusto y sirve.",
        category = RecipeCategory.ENTRANTE
    ),
    Recipe(
        name = "Sopa de Verduras",
        requiredIngredients = listOf("Patatas", "Zanahorias", "Calabacín", "Cebolla", "Aceite de Oliva", "Sal"),
        imageRes = R.drawable.sopa_verduras,
        instructions = "1. Sofríe cebolla en aceite. 2. Añade patatas, zanahorias y calabacín troceados. 3. Cubre con agua y cocina hasta tiernos. 4. Salpimenta y sirve.",
        category = RecipeCategory.ENTRANTE
    ),
    Recipe(
        name = "Yogur con Frutas y Almendras",
        requiredIngredients = listOf("Yogur", "Plátano", "Fresas", "Almendra", "Azúcar"),
        imageRes = R.drawable.yogur_frutas_almendras,
        instructions = "1. Corta las frutas. 2. Sirve yogur en bol, añade frutas y almendras. 3. Espolvorea azúcar y disfruta.",
        category = RecipeCategory.POSTRE
    ),
    Recipe(
        name = "Patatas Bravas",
        requiredIngredients = listOf("Patatas", "Tomate Frito", "Aceite de Oliva", "Sal", "Pimienta"),
        imageRes = R.drawable.patatas_bravas,
        instructions = "1. Corta patatas en dados y fríelas. 2. Calienta tomate frito con aceite y pimienta. 3. Sirve las patatas con la salsa por encima.",
        category = RecipeCategory.ENTRANTE
    ),
    Recipe(
        name = "Pan de Ajo",
        requiredIngredients = listOf("Pan", "Ajo", "Mantequilla", "Sal"),
        imageRes = R.drawable.pan_de_ajo,
        instructions = "1. Mezcla ajo picado con mantequilla y sal. 2. Unta el pan. 3. Gratina al horno hasta dorar.",
        category = RecipeCategory.ENTRANTE
    ),
    Recipe(
        name = "Tortilla de Brócoli",
        requiredIngredients = listOf("Huevos", "Brócoli", "Queso", "Sal", "Pimienta"),
        imageRes = R.drawable.tortilla_brocoli,
        instructions = "1. Cuece el brócoli y trocéalo. 2. Bate huevos con queso, sal y pimienta. 3. Añade brócoli y cuaja la tortilla.",
        category = RecipeCategory.DESAYUNO
    ),
    Recipe(
        name = "Croquetas de Patata",
        requiredIngredients = listOf("Patatas", "Harina", "Leche", "Pan Rallado", "Sal", "Aceite de Oliva"),
        imageRes = R.drawable.croquetas_patata,
        instructions = "1. Haz puré de patatas. 2. Forma croquetas, pásalas por harina, huevo y pan rallado. 3. Fríelas hasta dorar.",
        category = RecipeCategory.ENTRANTE
    ),
    Recipe(
        name = "Tostadas Francesas",
        requiredIngredients = listOf("Pan", "Huevos", "Leche", "Azúcar", "Mantequilla"),
        imageRes = R.drawable.tostadas_francesas,
        instructions = "1. Bate huevos con leche y azúcar. 2. Empapa el pan. 3. Fríe en mantequilla hasta dorar.",
        category = RecipeCategory.DESAYUNO
    ),
    Recipe(
        name = "Arroz con Leche",
        requiredIngredients = listOf("Arroz", "Leche", "Azúcar", "Canela"),
        imageRes = R.drawable.arroz_leche,
        instructions = "1. Cuece arroz en leche. 2. Añade azúcar y canela. 3. Cocina hasta espesar.",
        category = RecipeCategory.POSTRE
    ),
    Recipe(
        name = "Espaguetis Ajo y Aceite",
        requiredIngredients = listOf("Spaguetti", "Ajo", "Aceite de Oliva", "Sal", "Pimienta"),
        imageRes = R.drawable.spaguetti_ajo_aceite,
        instructions = "1. Cuece los espaguetis. 2. Dora ajos laminados en aceite. 3. Mezcla la pasta con el aceite y sazona.",
        category = RecipeCategory.PRINCIPAL
    ),
    Recipe(
        name = "Pollo con Patatas al Horno",
        requiredIngredients = listOf("Pollo", "Patatas", "Ajo", "Aceite de Oliva", "Sal", "Pimienta"),
        imageRes = R.drawable.pollor_patatas_al_horno,
        instructions = "1. Marina pollo y patatas con ajo, aceite, sal y pimienta. 2. Hornea a 200°C durante 40 minutos.",
        category = RecipeCategory.PRINCIPAL
    ),
    Recipe(
        name = "Yogur con Nueces",
        requiredIngredients = listOf("Yogur", "Nuez", "Miel"),
        imageRes = R.drawable.yogur_nueces,
        instructions = "1. Sirve yogur en un bol. 2. Añade nueces y un chorrito de miel.",
        category = RecipeCategory.POSTRE
    ),
    Recipe(
        name = "Manzana a la Plancha",
        requiredIngredients = listOf("Manzana", "Mantequilla", "Azúcar"),
        imageRes = R.drawable.manzana_plancha,
        instructions = "1. Corta manzana en rodajas. 2. Dora en mantequilla y espolvorea azúcar. 3. Cocina hasta caramelizar.",
        category = RecipeCategory.POSTRE
    )
)
