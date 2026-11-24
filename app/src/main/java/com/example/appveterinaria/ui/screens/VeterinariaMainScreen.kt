package com.example.appveterinaria.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appveterinaria.ui.theme.BorderWarm
import com.example.appveterinaria.ui.theme.CanvasCream
import com.example.appveterinaria.ui.theme.EmeraldBrand
import com.example.appveterinaria.ui.theme.EmeraldTextDark
import com.example.appveterinaria.ui.theme.StoneMuted
import com.example.appveterinaria.ui.theme.SurfaceWhite
import com.example.appveterinaria.ui.viewmodel.VeterinariaViewModel

@Composable
fun VeterinariaMainScreen(
    modifier: Modifier = Modifier,
    vm: VeterinariaViewModel = viewModel()
) {
    val pantallaActual by vm.pantallaActual.observeAsState("inicio")
    val cargando by vm.cargando.observeAsState(false)
    val mascotas by vm.mascotas.observeAsState(emptyList())
    val consultas by vm.consultas.observeAsState(emptyList())
    val catalogo by vm.catalogo.observeAsState(emptyList())
    val pedidos by vm.pedidos.observeAsState(emptyList())
    val carrito by vm.carrito.observeAsState(emptyMap())
    val mensajeEstado by vm.mensajeEstado.observeAsState("")

    val totalClientes by vm.totalClientes.observeAsState(0)
    val totalPedidos by vm.totalPedidos.observeAsState(0)
    val ultimoCliente by vm.ultimoCliente.observeAsState("Sin registros")
    val totalCarrito by vm.totalCarrito.observeAsState(0.0)
    val cantidadCarrito by vm.cantidadCarrito.observeAsState(0)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(CanvasCream)
    ) {
        when (pantallaActual) {
            "inicio" -> {
                InicioScreen(
                    totalClientes = totalClientes,
                    totalPedidos = totalPedidos,
                    ultimoCliente = ultimoCliente,
                    mascotas = mascotas,
                    consultas = consultas,
                    pedidos = pedidos,
                    cargando = cargando,
                    onIrARegistro = { vm.navegarA("registro") },
                    onIrACatalogo = { vm.navegarA("catalogo") },
                    onRecargar = { vm.cargarDatos() }
                )
            }
            "registro" -> {
                RegistroScreen(
                    totalClientes = totalClientes,
                    totalPedidos = totalPedidos,
                    ultimoCliente = ultimoCliente,
                    mensajeEstado = mensajeEstado,
                    onRegistrar = { nombreDueno, correo, telefono, direccion, nombreMascota, especie, raza, edad ->
                        vm.registrarClienteYMascota(
                            nombreDueno = nombreDueno,
                            correo = correo,
                            telefono = telefono,
                            direccion = direccion,
                            nombreMascota = nombreMascota,
                            especie = especie,
                            raza = raza,
                            edadTexto = edad
                        )
                    },
                    onLimpiarMensaje = { vm.limpiarMensaje() },
                    onIrACatalogo = { vm.navegarA("catalogo") }
                )
            }
            "catalogo" -> {
                CatalogoScreen(
                    totalClientes = totalClientes,
                    totalPedidos = totalPedidos,
                    ultimoCliente = ultimoCliente,
                    ultimaMascota = mascotas.lastOrNull(),
                    catalogo = catalogo,
                    carrito = carrito,
                    totalCarrito = totalCarrito,
                    cantidadCarrito = cantidadCarrito,
                    cargando = cargando,
                    mensajeEstado = mensajeEstado,
                    onAgregar = { vm.agregarProducto(it) },
                    onQuitar = { vm.quitarProducto(it) },
                    onVaciar = { vm.vaciarCarrito() },
                    onConfirmar = { vm.confirmarPedido() }
                )
            }
        }

        // Barra de navegación inferior
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(SurfaceWhite)
        ) {
            HorizontalDivider(thickness = 1.dp, color = BorderWarm)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NavItem(
                    icono = "🏥",
                    etiqueta = "Inicio",
                    activo = pantallaActual == "inicio",
                    onClick = { vm.navegarA("inicio") }
                )
                NavItem(
                    icono = "🐾",
                    etiqueta = "Registro",
                    activo = pantallaActual == "registro",
                    onClick = { vm.navegarA("registro") }
                )
                NavItem(
                    icono = "📅",
                    etiqueta = "Servicios",
                    activo = pantallaActual == "catalogo",
                    onClick = { vm.navegarA("catalogo") }
                )
            }
        }
    }
}

@Composable
private fun NavItem(
    icono: String,
    etiqueta: String,
    activo: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 2.dp)
    ) {
        Text(text = icono, fontSize = 18.sp)
        Text(
            text = etiqueta,
            fontSize = 11.sp,
            fontWeight = if (activo) FontWeight.ExtraBold else FontWeight.Medium,
            color = if (activo) EmeraldTextDark else StoneMuted
        )
        Spacer(modifier = Modifier.height(3.dp))
        if (activo) {
            Box(
                modifier = Modifier
                    .size(5.dp)
                    .background(EmeraldBrand, CircleShape)
            )
        } else {
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}
