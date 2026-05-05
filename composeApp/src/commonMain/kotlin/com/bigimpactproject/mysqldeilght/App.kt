package com.bigimpactproject.mysqldeilght

import BdHelper
import DatabaseFactory
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bigimpactproject.mysqldeilght.remote.ApiService
import com.bigimpactproject.mysqldeilght.remote.HttpclientProv
import com.bigimpactproject.mysqldeilght.remote.MyRipository
import kotlinx.coroutines.launch

@Composable
fun App(
    databaseFactory: DatabaseFactory,
) {
    MaterialTheme {
        val bdHelper = BdHelper(databaseFactory)
        val dbFetchers = DatabaseFetchersImpl(bdHelper)
        val scope = rememberCoroutineScope()
        var update by remember { mutableStateOf(false) }
        val notes = remember { mutableStateListOf<Notes?>(null)}
        val apiService = ApiService()
        val  myRepository = MyRipository(
           apiService = apiService,
            databaseFetchers = dbFetchers
        )
        Column (
            modifier = Modifier.safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            if (isWebTarget()){
                Button(
                    onClick = {
                        scope.launch {
                            val response =myRepository.getForWeb()
                            notes.clear()
                            notes.addAll(response)
                        }
                    }
                ){
                    Text(text = "Insert Note")
                }
            }else{
                Button(
                    onClick = {
                        scope.launch {
                            val response =myRepository.getElement()
                            notes.clear()
                            notes.addAll(response)
                        }
                    }
                ){
                    Text(text = "Insert Note")
                }
            }

            LazyColumn {
                items(notes){note->
                    if (note == null){
                    Text("No notes available")
                    }else{
                        Row(
                            modifier = Modifier.padding(16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Text(" ${note.title}  ${note.body}")
                            Button(
                                onClick = {
                                    scope.launch {
                                        dbFetchers.deleteNote(id = note.id!!)
                                    }
                                    notes.remove(note)
                                    update = !update
                                }

                            ){
                                Text(text = "Delete")
                            }
                        }
                    }


                }
            }
        }
    }


}


expect fun isWebTarget(): Boolean