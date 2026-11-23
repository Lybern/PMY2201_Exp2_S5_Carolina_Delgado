package com.example.appveterinaria.data.model

import java.util.UUID

data class Mascota(
    val id: String = UUID.randomUUID().toString(),
    val nombre: String,
    val especie: String,
    val raza: String,
    val edad: Int,
    val dueno: Cliente
)
