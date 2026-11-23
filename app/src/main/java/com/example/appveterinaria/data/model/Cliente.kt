package com.example.appveterinaria.data.model

import java.util.UUID

data class Cliente(
    val id: String = UUID.randomUUID().toString(),
    val nombre: String,
    val telefono: String,
    val correo: String,
    val direccion: String
)
