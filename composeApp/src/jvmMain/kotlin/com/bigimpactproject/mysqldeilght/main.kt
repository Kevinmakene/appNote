package com.bigimpactproject.mysqldeilght

import DatabaseFactory
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "MySqlDeilght",
    ) {
        App(
            databaseFactory = DatabaseFactory(),
        )
    }
}