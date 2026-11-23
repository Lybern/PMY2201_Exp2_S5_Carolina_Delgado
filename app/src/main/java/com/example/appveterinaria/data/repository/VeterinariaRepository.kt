package com.example.appveterinaria.data.repository

import com.example.appveterinaria.data.model.CategoriaProducto
import com.example.appveterinaria.data.model.Cliente
import com.example.appveterinaria.data.model.Consulta
import com.example.appveterinaria.data.model.Mascota
import com.example.appveterinaria.data.model.Pedido
import com.example.appveterinaria.data.model.Producto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

data class DatosInicialesVeterinaria(
    val clientes: List<Cliente>,
    val mascotas: List<Mascota>,
    val catalogo: List<Producto>,
    val consultas: List<Consulta>,
    val pedidos: List<Pedido>
)

class VeterinariaRepository {

    private val catalogoServicios = listOf(
        Producto(1, "Consulta Clínica General", CategoriaProducto.CONSULTA, 25000.0),
        Producto(2, "Vacuna Séxtuple / Triple Felina", CategoriaProducto.VACUNA, 18500.0),
        Producto(3, "Pack Control Sano & Desparasitación", CategoriaProducto.CONSULTA, 34990.0),
        Producto(4, "Ecografía Abdominal", CategoriaProducto.EXAMEN, 42000.0),
        Producto(5, "Alimento Terapéutico 3kg", CategoriaProducto.FARMACIA, 29900.0),
        Producto(6, "Profilaxis Dental Ultrasónica", CategoriaProducto.CONSULTA, 48000.0)
    )

    private val clientesIniciales = listOf(
        Cliente(
            nombre = "Carolina Delgado",
            telefono = "+56 9 8765 4321",
            correo = "carolina@vetcare.cl",
            direccion = "Av. Providencia 1420, Santiago"
        ),
        Cliente(
            nombre = "Matías Rojas",
            telefono = "+56 9 9123 4567",
            correo = "matias.rojas@vetcare.cl",
            direccion = "Av. Apoquindo 845, Las Condes"
        )
    )

    private val mascotasIniciales = listOf(
        Mascota(
            nombre = "Luna",
            especie = "Perro",
            raza = "Golden Retriever",
            edad = 3,
            dueno = clientesIniciales[0]
        ),
        Mascota(
            nombre = "Milo",
            especie = "Gato",
            raza = "Siamés",
            edad = 2,
            dueno = clientesIniciales[1]
        )
    )

    private val consultasIniciales = listOf(
        Consulta(
            mascota = mascotasIniciales[0],
            motivo = "Control sano anual y refuerzo de vacuna",
            veterinario = "Dra. Camila Herrera",
            costo = 34990.0,
            estado = "En atención"
        ),
        Consulta(
            mascota = mascotasIniciales[1],
            motivo = "Chequeo general y control de peso",
            veterinario = "Dra. Sofía Valdés",
            costo = 25000.0,
            estado = "Completado"
        )
    )

    private val pedidosIniciales = listOf(
        Pedido(
            cliente = clientesIniciales[0],
            mascota = mascotasIniciales[0],
            productos = listOf(catalogoServicios[2], catalogoServicios[1])
        ),
        Pedido(
            cliente = clientesIniciales[1],
            mascota = mascotasIniciales[1],
            productos = listOf(catalogoServicios[0], catalogoServicios[4])
        )
    )

    suspend fun cargarDatosIniciales(): DatosInicialesVeterinaria = withContext(Dispatchers.IO) {
        delay(1200)
        DatosInicialesVeterinaria(
            clientes = clientesIniciales,
            mascotas = mascotasIniciales,
            catalogo = catalogoServicios,
            consultas = consultasIniciales,
            pedidos = pedidosIniciales
        )
    }

    suspend fun registrarPedidoAsync(
        cliente: Cliente,
        mascota: Mascota?,
        productos: List<Producto>
    ): Pair<Pedido, Consulta?> = withContext(Dispatchers.IO) {
        delay(600)
        val nuevoPedido = Pedido(
            cliente = cliente,
            mascota = mascota,
            productos = productos
        )
        val nuevaConsulta = mascota?.let {
            Consulta(
                mascota = it,
                motivo = productos.joinToString(", ") { prod -> prod.nombre },
                veterinario = "Dra. Camila Herrera",
                costo = nuevoPedido.total
            )
        }
        Pair(nuevoPedido, nuevaConsulta)
    }
}
