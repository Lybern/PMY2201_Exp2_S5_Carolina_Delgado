package com.example.appveterinaria.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.appveterinaria.data.model.Cliente
import com.example.appveterinaria.data.model.Consulta
import com.example.appveterinaria.data.model.Mascota
import com.example.appveterinaria.data.model.Pedido
import com.example.appveterinaria.data.model.Producto
import com.example.appveterinaria.data.repository.VeterinariaRepository
import com.example.appveterinaria.util.Validaciones
import kotlinx.coroutines.launch

class VeterinariaViewModel(
    private val repository: VeterinariaRepository = VeterinariaRepository()
) : ViewModel() {

    private val _pantallaActual = MutableLiveData("inicio")
    val pantallaActual: LiveData<String> = _pantallaActual

    private val _cargando = MutableLiveData(false)
    val cargando: LiveData<Boolean> = _cargando

    private val _clientes = MutableLiveData<List<Cliente>>(emptyList())
    val clientes: LiveData<List<Cliente>> = _clientes

    private val _mascotas = MutableLiveData<List<Mascota>>(emptyList())
    val mascotas: LiveData<List<Mascota>> = _mascotas

    private val _consultas = MutableLiveData<List<Consulta>>(emptyList())
    val consultas: LiveData<List<Consulta>> = _consultas

    private val _catalogo = MutableLiveData<List<Producto>>(emptyList())
    val catalogo: LiveData<List<Producto>> = _catalogo

    private val _pedidos = MutableLiveData<List<Pedido>>(emptyList())
    val pedidos: LiveData<List<Pedido>> = _pedidos

    private val _carrito = MutableLiveData<Map<Producto, Int>>(emptyMap())
    val carrito: LiveData<Map<Producto, Int>> = _carrito

    private val _mensajeEstado = MutableLiveData("")
    val mensajeEstado: LiveData<String> = _mensajeEstado

    val totalClientes: LiveData<Int> = clientes.map { it.size }
    val totalPedidos: LiveData<Int> = pedidos.map { it.size }
    val ultimoCliente: LiveData<String> = clientes.map { lista ->
        lista.lastOrNull()?.nombre ?: "Sin registros"
    }

    val totalCarrito: LiveData<Double> = carrito.map { mapa ->
        val subtotal = mapa.entries.sumOf { (producto, cantidad) -> producto.precio * cantidad }
        subtotal * 1.19
    }

    val cantidadCarrito: LiveData<Int> = carrito.map { mapa ->
        mapa.values.sum()
    }

    init {
        cargarDatos()
    }

    fun cargarDatos() {
        viewModelScope.launch {
            _cargando.value = true
            val datos = repository.cargarDatosIniciales()
            _clientes.value = datos.clientes
            _mascotas.value = datos.mascotas
            _catalogo.value = datos.catalogo
            _consultas.value = datos.consultas
            _pedidos.value = datos.pedidos
            _cargando.value = false
        }
    }

    fun navegarA(pantalla: String) {
        _mensajeEstado.value = ""
        _pantallaActual.value = pantalla
    }

    fun limpiarMensaje() {
        _mensajeEstado.value = ""
    }

    fun registrarClienteYMascota(
        nombreDueno: String,
        correo: String,
        telefono: String,
        direccion: String,
        nombreMascota: String,
        especie: String,
        raza: String,
        edadTexto: String
    ): Boolean {
        val error = Validaciones.validarRegistro(
            nombreDueno = nombreDueno,
            correo = correo,
            telefono = telefono,
            nombreMascota = nombreMascota,
            edadTexto = edadTexto
        )
        if (error != null) {
            _mensajeEstado.value = error
            return false
        }

        val nuevoCliente = Cliente(
            nombre = nombreDueno.trim(),
            telefono = telefono.trim(),
            correo = correo.trim(),
            direccion = direccion.ifBlank { "Santiago" }.trim()
        )
        val nuevaMascota = Mascota(
            nombre = nombreMascota.trim(),
            especie = especie.trim(),
            raza = raza.ifBlank { "Mestizo" }.trim(),
            edad = edadTexto.filter { it.isDigit() }.toInt(),
            dueno = nuevoCliente
        )

        _clientes.value = _clientes.value.orEmpty() + nuevoCliente
        _mascotas.value = _mascotas.value.orEmpty() + nuevaMascota
        _mensajeEstado.value =
            "Registrado: ${nuevoCliente.nombre} con ${nuevaMascota.nombre} (${nuevaMascota.especie}). Total clientes: ${_clientes.value?.size}"
        return true
    }

    fun agregarProducto(producto: Producto) {
        val actual = _carrito.value.orEmpty()
        _carrito.value = actual + (producto to (actual[producto] ?: 0) + 1)
    }

    fun quitarProducto(producto: Producto) {
        val actual = _carrito.value.orEmpty().toMutableMap()
        val cantidad = actual[producto] ?: return
        if (cantidad <= 1) {
            actual.remove(producto)
        } else {
            actual[producto] = cantidad - 1
        }
        _carrito.value = actual
    }

    fun vaciarCarrito() {
        _carrito.value = emptyMap()
    }

    fun confirmarPedido() {
        val productosSeleccionados = _carrito.value.orEmpty()
            .flatMap { (producto, cantidad) -> List(cantidad) { producto } }

        if (productosSeleccionados.isEmpty()) {
            _mensajeEstado.value = "Agrega al menos un servicio o producto antes de confirmar."
            return
        }

        val clienteActual = _clientes.value?.lastOrNull() ?: return
        val mascotaActual = _mascotas.value?.lastOrNull { it.dueno.id == clienteActual.id }
            ?: _mascotas.value?.lastOrNull()

        viewModelScope.launch {
            _cargando.value = true
            val (nuevoPedido, nuevaConsulta) = repository.registrarPedidoAsync(
                cliente = clienteActual,
                mascota = mascotaActual,
                productos = productosSeleccionados
            )
            _pedidos.value = _pedidos.value.orEmpty() + nuevoPedido
            if (nuevaConsulta != null) {
                _consultas.value = _consultas.value.orEmpty() + nuevaConsulta
            }
            _carrito.value = emptyMap()
            _cargando.value = false
            _mensajeEstado.value =
                "Pedido #${nuevoPedido.id.take(5).uppercase()} confirmado para ${clienteActual.nombre}. Total pedidos: ${_pedidos.value?.size}"
        }
    }
}
