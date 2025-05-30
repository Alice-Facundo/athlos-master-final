package com.example.athlos.ui.screens

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.athlos.ui.theme.AthlosTheme
import com.example.athlos.ui.theme.DarkGreen // Importar DarkGreen

@Composable
fun DiaryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // USANDO COR DO TEMA
            .padding(16.dp)
            .verticalScroll(rememberScrollState()), // HABILITANDO ROLAGEM
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TextView tv_total_kcal_label
        Text(
            text = "1200 kcal",
            fontSize = 24.sp,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimary, // Cor do texto baseada no tema
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(DarkGreen) // USANDO DARKGREEN DO SEU TEMA
                .wrapContentSize(Alignment.Center)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // layout_macros (Row com ProgressBars e Textos)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MacroProgressItem(
                label = "CARBOIDRATO",
                progress = 70,
                progressColor = Color(0xFFFFC107) // Cor específica para carboidrato
            )
            MacroProgressItem(
                label = "PROTEÍNA",
                progress = 85,
                progressColor = Color(0xFFD32F2F) // Cor específica para proteína
            )
            MacroProgressItem(
                label = "FIBRAS",
                progress = 60,
                progressColor = Color(0xFF4CAF50) // Cor específica para fibras
            )
        }

        // Seções de Refeição (Café, Almoço, Jantar, Lanches)
        // Não é mais necessário um Column aninhado com weight(1f, fill=false)
        // porque o verticalScroll já está no Column pai.
        MealSection(title = "CAFÉ")
        MealSection(title = "ALMOÇO")
        MealSection(title = "JANTAR")
        MealSection(title = "LANCHES/OUTROS")
    }
}

@Composable
fun MealSection(title: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onBackground, // USANDO COR DO TEMA
            fontSize = 18.sp,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 32.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "",
            onValueChange = { /* TODO: Implementar lógica de estado */ },
            label = { Text("Adicionar refeição") }, // Texto do label, a cor é definida no `defaultTextFieldColors`
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = defaultTextFieldColors() // USANDO CORES DO TEMA PARA CAMPOS DE TEXTO
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "",
            onValueChange = { /* TODO: Implementar lógica de estado */ },
            label = { Text("Adicionar refeição") }, // Texto do label, a cor é definida no `defaultTextFieldColors`
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = defaultTextFieldColors() // USANDO CORES DO TEMA PARA CAMPOS DE TEXTO
        )
    }
}

@Composable
fun MacroProgressItem(label: String, progress: Int, progressColor: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val strokeWidth = 8.dp
        ProgressRing(progress, progressColor, strokeWidth) // Chamando o novo Composable para o Canvas
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onBackground, // USANDO COR DO TEMA
            fontSize = 12.sp,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
fun ProgressRing(progress: Int, progressColor: Color, strokeWidth: Dp) {
    val animatedProgress = remember { androidx.compose.animation.core.Animatable(0f) }

    LaunchedEffect(progress) {
        animatedProgress.animateTo(
            targetValue = progress / 100f,
            animationSpec = tween(durationMillis = 1000)
        )
    }

    // COR DE FUNDO DO CÍRCULO, USANDO CONTEXTO COMPOSABLE
    val backgroundRingColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)

    Canvas(modifier = Modifier.size(80.dp)) {
        val radius = size.minDimension / 2f

        // Desenha o fundo cinza do anel
        drawCircle(
            color = backgroundRingColor,
            radius = radius,
            style = Stroke(width = strokeWidth.toPx())
        )

        // Desenha o progresso como um arco
        drawArc(
            color = progressColor,
            startAngle = 270f,
            sweepAngle = animatedProgress.value * 360f,
            useCenter = false,
            topLeft = Offset(strokeWidth.toPx() / 2, strokeWidth.toPx() / 2),
            size = Size(size.width - strokeWidth.toPx(), size.height - strokeWidth.toPx()),
            style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
        )
    }
}


@Composable
private fun defaultTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedTextColor = MaterialTheme.colorScheme.onSurface,
    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
    cursorColor = MaterialTheme.colorScheme.primary,
    focusedContainerColor = MaterialTheme.colorScheme.surface, // USANDO CORES DO TEMA
    unfocusedContainerColor = MaterialTheme.colorScheme.surface, // USANDO CORES DO TEMA
    focusedLabelColor = MaterialTheme.colorScheme.onSurface, // USANDO CORES DO TEMA
    unfocusedLabelColor = MaterialTheme.colorScheme.onSurface, // USANDO CORES DO TEMA
    focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f), // USANDO CORES DO TEMA
    unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f) // USANDO CORES DO TEMA
)

@Preview(showBackground = true)
@Composable
fun PreviewDiaryScreen() {
    AthlosTheme {
        DiaryScreen()
    }
}