package com.noxcrew.launchy.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowScope
import com.noxcrew.launchy.LocalLaunchyState
import com.noxcrew.launchy.ui.screens.settings.Browser
import com.noxcrew.launchy.ui.state.windowScope

@Composable
fun FirstLaunchDialog() {
    val state = LocalLaunchyState
    if (!state.handledFirstLaunch) {
        FirstLaunchDialog(
            windowScope,
            onAccept = {
                state.handledFirstLaunch = true
            }
        )
    }
}

@Composable
fun FirstLaunchDialog(
    windowScope: WindowScope,
    onAccept: () -> Unit,
) {
    // Overlay that prevents clicking behind it
    windowScope.WindowDraggableArea {
        Box(Modifier.background(MaterialTheme.colorScheme.surface.copy(alpha = 0.6f)).fillMaxSize())
    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(20.dp),
            color = MaterialTheme.colorScheme.surface,
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    "Welcome to Launchy!",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 10.dp),
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    """
                        Launchy is a Minecraft Modpack Installer for Minecraft originally created by
                        Mine in Abyss and updated by the Wynncraft team. This is a version configured
                        to install recommended mods for MC Championship based on their original work.
                        
                        This program will install a MC Championship profile for you in the Minecraft launcher.
                        If you don't have the official launcher installed, you can download it by clicking the button below.
                        You can also select extra optional mods to install in the settings screen.                        
                    """.trimIndent(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 10.dp),
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(onClick = {
                        Browser.browse("https://www.minecraft.net/en-us/download/")
                    }) {
                        Text("Get Minecraft Launcher", color = MaterialTheme.colorScheme.primary)
                    }
                    TextButton(onClick = onAccept) {
                        Text("Ok", color = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        }
    }
}
