package com.androidcourse.laboratorio14animaciones

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.androidcourse.laboratorio14animaciones.ui.theme.Laboratorio14AnimacionesTheme

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AnimatedScreenExample() {
    var isExpanded by remember { mutableStateOf(false) }
    var isVisible by remember { mutableStateOf(true) }
    var isLightMode by remember { mutableStateOf(true) }

    val boxSize by animateDpAsState(
        targetValue = if (isExpanded) 150.dp else 100.dp,
        animationSpec = tween(durationMillis = 500)
    )
    val boxColor by animateColorAsState(
        targetValue = if (isExpanded) Color.Red else Color.Blue,
        animationSpec = tween(durationMillis = 500)
    )

    Laboratorio14AnimacionesTheme(darkTheme = !isLightMode) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Ejemplo de Animaciones") }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(boxSize)
                        .background(boxColor)
                        .clickable { isExpanded = !isExpanded }
                )

                Spacer(modifier = Modifier.height(20.dp))

                AnimatedVisibility(
                    visible = isVisible,
                    enter = slideInVertically(animationSpec = tween(500)) + fadeIn(animationSpec = tween(500)),
                    exit = slideOutVertically(animationSpec = tween(500)) + fadeOut(animationSpec = tween(500))
                ) {
                    Button(onClick = { isVisible = !isVisible }) {
                        Text(text = "Alternar Visibilidad")
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                AnimatedContent(
                    targetState = isLightMode,
                    transitionSpec = {
                        fadeIn(animationSpec = tween(500)) with fadeOut(animationSpec = tween(500))
                    }
                ) { targetState ->
                    if (targetState) {
                        Text(text = "Modo Claro", color = Color.Black)
                    } else {
                        Text(text = "Modo Oscuro", color = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = { isLightMode = !isLightMode }) {
                    Text(text = "Cambiar Modo")
                }
            }
        }
    }
}