package com.example.appveterinaria.data.model

import java.util.UUID

data class Pedido(
    val id: String = UUID.randomUUID().toString(),
    val cliente: Cliente,
    val mascota: Mascota? = null,
    val productos: List<Producto>,
    val estado: String = "Registrado"
) {
    val subtotal: Double
        get() = productos.sumOf { it.precio }

    val iva: Double
        get() = subtotal * 0.19

    val total: Double
        get() = subtotal + iva
}
