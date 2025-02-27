package com.noxcrew.launchy.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.ResourceLoader
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowScope
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

@Composable
fun BoxScope.BackgroundImage(windowScope: WindowScope) {
    windowScope.WindowDraggableArea {
        Image(
            painter = painterResource("background.png"),
            contentDescription = "Main render",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
    BackgroundTint()
}

@Composable
fun BoxScope.BackgroundTint() {
    val colors = listOf(
        Color.Transparent,
        MaterialTheme.colorScheme.background,
    )

    BoxWithConstraints(Modifier.align(Alignment.BottomCenter)) {
        Spacer(
            Modifier
                .fillMaxWidth()
                .height(maxHeight / 2)
                .background(Brush.verticalGradient(colors))
        )
    }
}

@Composable
fun LogoLarge(modifier: Modifier) {
    Image(
        painter = painterResource("logo.png"),
        contentDescription = "Logo",
        modifier = Modifier.widthIn(0.dp, 400.dp).then(modifier),
        contentScale = ContentScale.FillWidth
    )
}
