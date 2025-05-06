package com.example.tfg_aplicacion.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tfg_aplicacion.R
import com.example.tfg_aplicacion.ui.theme.ContentBackground
import com.example.tfg_aplicacion.ui.theme.IconDark

//Modelo para cada supermercado
data class Supermarket(
    val name: String,
    val iconRes: Int,
    val url: String
)

//Lista de supermercados con venta online en España
private val supermarkets = listOf(
    Supermarket("Mercadona",              R.drawable.ic_launcher_foreground, "https://www.mercadona.es"),
    Supermarket("Carrefour",              R.drawable.ic_launcher_foreground, "https://www.carrefour.es"),
    Supermarket("Lidl",                   R.drawable.ic_launcher_foreground, "https://www.lidl.es"),
    Supermarket("Alcampo",                R.drawable.ic_launcher_foreground, "https://www.alcampo.es"),
    Supermarket("Eroski",                 R.drawable.ic_launcher_foreground, "https://www.eroski.es"),
    Supermarket("DIA",                    R.drawable.ic_launcher_foreground, "https://www.dia.es"),
    Supermarket("Consum",                 R.drawable.ic_launcher_foreground, "https://www.consum.es"),
    Supermarket("Ahorramás",              R.drawable.ic_launcher_foreground, "https://www.ahorramas.com"),
    Supermarket("El Corte Inglés (Supercor)", R.drawable.ic_launcher_foreground, "https://www.elcorteingles.es/supermercado/"),
    Supermarket("Hipercor",               R.drawable.ic_launcher_foreground, "https://www.hipercor.es"),
    Supermarket("Caprabo",                R.drawable.ic_launcher_foreground, "https://www.caprabo.com"),
    Supermarket("Covirán",                R.drawable.ic_launcher_foreground, "https://www.coviran.es"),
    Supermarket("Bon Preu",               R.drawable.ic_launcher_foreground, "https://www.bonpreuesclat.cat"),
    Supermarket("Froiz",                  R.drawable.ic_launcher_foreground, "https://www.froiz.es"),
    Supermarket("Alimerka",               R.drawable.ic_launcher_foreground, "https://www.alimerka.es"),
    Supermarket("Spar",                   R.drawable.ic_launcher_foreground, "https://www.spar.es"),
    Supermarket("Gadis",                  R.drawable.ic_launcher_foreground, "https://www.gadis.es"),
    Supermarket("BM Supermercados",       R.drawable.ic_launcher_foreground, "https://www.bmsupermercados.com"),
)

@Composable
fun SupermarketsScreen(modifier: Modifier = Modifier) {
    val ctx = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ContentBackground)
            .padding(16.dp)
    ) {
        Text(
            text = "Supermercados Online",
            style = MaterialTheme.typography.headlineMedium,
            color = IconDark
        )
        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(supermarkets) { market ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            // Abrir navegador con la URL del supermercado
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(market.url))
                            ctx.startActivity(intent)
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = market.iconRes),
                            contentDescription = "Logo de ${market.name}",
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Text(
                            text = market.name,
                            style = MaterialTheme.typography.bodyLarge,
                            color = IconDark
                        )
                    }
                }
            }
        }
    }
}
