package com.bigimpactproject.mysqldeilght

import DatabaseFactory
import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController {
    App(
        databaseFactory = DatabaseFactory(),
    )
}