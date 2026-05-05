package com.bigimpactproject.mysqldeilght

import kotlinx.serialization.Serializable

@Serializable
data class Notes(
    val id : Long?,
    val title : String,
    val body : String
)
