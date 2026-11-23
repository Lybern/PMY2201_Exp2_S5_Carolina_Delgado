package com.example.appveterinaria.data.model

import java.util.UUID

data class Consulta(
    val id: String = UUID.randomUUID().toString(),
    val mascota: Mascota,
    val motivo: String,
    val veterinario: String,
    val costo: Double,
    val estado: String = "En atención"
)
