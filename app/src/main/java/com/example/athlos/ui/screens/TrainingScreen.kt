package com.example.athlos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale // Importar ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.athlos.R // Importar R para acessar drawables
import com.example.athlos.ui.models.TrainingItem

@Composable
fun TrainingScreen() {
    val trainingItems = listOf(
        TrainingItem("Treino de Peito", R.drawable.chest), // Certifique-se de que chest.png/xml existe em res/drawable
        TrainingItem("Treino de Costas", R.drawable.back),   // Certifique-se de que back.png/xml existe em res/drawable
        TrainingItem("Treino de Quadríceps", R.drawable.quads), // ...e assim por diante
        TrainingItem("Treino de Ombros", R.drawable.shoulder),
        TrainingItem("Treino de Bíceps", R.drawable.biceps),
        TrainingItem("Treino de Tríceps", R.drawable.triceps),
        TrainingItem("Treino de Abdômen", R.drawable.abs),
        TrainingItem("Treino de Glúteos", R.drawable.glutes),
        TrainingItem("Treino de Dorsal", R.drawable.dorsal),
        TrainingItem("Treino de Posterior", R.drawable.hamstrings),
        TrainingItem("Treino de Oblíquos", R.drawable.obliquo),
        TrainingItem("Treino de Trapezio", R.drawable.trapezius),
        TrainingItem("Treino de Panturrilha", R.drawable.calfs),
        TrainingItem("Treino de Antebraço", R.drawable.forearm)
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            "Treinos",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp) // Adiciona um pequeno padding abaixo do título
        )

        LazyColumn {
            items(trainingItems) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp), // Espaçamento vertical entre os cartões
                    elevation = CardDefaults.cardElevation(4.dp) // Elevação para dar um efeito de sombra
                ) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        Image(
                            painter = painterResource(id = item.imageResId),
                            contentDescription = item.title,
                            modifier = Modifier.size(64.dp),
                            contentScale = ContentScale.Crop // Adiciona ContentScale para ajustar a imagem
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.titleMedium, // Estilo para o título do treino
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}