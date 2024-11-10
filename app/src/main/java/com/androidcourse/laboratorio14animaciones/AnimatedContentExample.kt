package com.androidcourse.laboratorio14animaciones

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

enum class ContentState {
    Cargando, Contenido, Error
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentExample() {
    var currentState by remember { mutableStateOf(ContentState.Cargando) }

    LaunchedEffect(Unit) {
        delay(2000)
        currentState = ContentState.Contenido
        delay(2000)
        currentState = ContentState.Error
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedContent(
            targetState = currentState,
            transitionSpec = {
                fadeIn(animationSpec = tween(1000)) with fadeOut(animationSpec = tween(1000))
            }
        ) { targetState ->
            when (targetState) {
                ContentState.Cargando -> Text(text = "Cargando...")
                ContentState.Contenido -> Text(text = "Contenido cargado")
                ContentState.Error -> Text(text = "Error al cargar contenido")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            currentState = when (currentState) {
                ContentState.Cargando -> ContentState.Contenido
                ContentState.Contenido -> ContentState.Error
                ContentState.Error -> ContentState.Cargando
            }
        }) {
            Text(text = "Cambiar Estado")
        }
    }
}