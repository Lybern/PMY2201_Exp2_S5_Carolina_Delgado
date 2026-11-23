package com.example.appveterinaria.data.model

enum class CategoriaProducto(val titulo: String) {
    CONSULTA("Consulta"),
    VACUNA("Vacuna"),
    FARMACIA("Farmacia"),
    EXAMEN("Examen")
}

data class Producto(
    val id: Int,
    val nombre: String,
    val categoria: CategoriaProducto,
    val precio: Double
)
