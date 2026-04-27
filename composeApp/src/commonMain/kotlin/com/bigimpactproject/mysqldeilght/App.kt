package com.bigimpactproject.mysqldeilght

import BdHelper
import DatabaseFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun App(
    databaseFactory: DatabaseFactory,
) {
    MaterialTheme {
        val bdHelper = BdHelper(databaseFactory)
        val dbFetchers = DatabaseFetchersImpl(bdHelper)
        val scope = rememberCoroutineScope ()
        var notes by remember { mutableStateOf<List<Notes>?>(null)}
        scope.launch {
            notes = dbFetchers.getNotes() as List<Notes>
        }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                Button(
                    onClick = {
                        scope.launch {
                            dbFetchers.insertNote(title = "New Note", description = "This is a new note")
                            notes = dbFetchers.getNotes() as List<Notes>
                        }
                    }
                ){
                    Text("click me")
                }
            }
        ) {
            LazyColumn (
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .safeContentPadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
               items(notes ?: emptyList()) {
                    MyCardFunction(notes = it)
               }

            }
        }
        }

}

@Composable
fun MyCardFunction(notes: Notes){
    OutlinedCard(
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = notes.title,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 12.sp
        )
        Text(
            text = notes.description,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp),
            fontSize = 8.sp
        )
    }
}

