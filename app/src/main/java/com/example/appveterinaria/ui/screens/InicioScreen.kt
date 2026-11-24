package com.example.appveterinaria.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appveterinaria.data.model.Cliente
import com.example.appveterinaria.data.model.Consulta
import com.example.appveterinaria.data.model.Mascota
import com.example.appveterinaria.data.model.Pedido
import com.example.appveterinaria.ui.theme.AppVeterinariaTheme
import com.example.appveterinaria.ui.theme.AmberBorder
import com.example.appveterinaria.ui.theme.AmberBrand
import com.example.appveterinaria.ui.theme.AmberCreamBg
import com.example.appveterinaria.ui.theme.AmberDeep
import com.example.appveterinaria.ui.theme.AmberGold
import com.example.appveterinaria.ui.theme.AmberSoftBg
import com.example.appveterinaria.ui.theme.AmberTextDark
import com.example.appveterinaria.ui.theme.BorderWarm
import com.example.appveterinaria.ui.theme.CanvasCream
import com.example.appveterinaria.ui.theme.EmeraldBrand
import com.example.appveterinaria.ui.theme.EmeraldDark
import com.example.appveterinaria.ui.theme.EmeraldMintBg
import com.example.appveterinaria.ui.theme.EmeraldSoftBg
import com.example.appveterinaria.ui.theme.EmeraldTextDark
import com.example.appveterinaria.ui.theme.StoneDark
import com.example.appveterinaria.ui.theme.StoneMuted
import com.example.appveterinaria.ui.theme.SurfaceTabTrack
import com.example.appveterinaria.ui.theme.SurfaceWarmLow
import com.example.appveterinaria.ui.theme.SurfaceWhite
import com.example.appveterinaria.ui.theme.TealBadgeBg
import com.example.appveterinaria.ui.theme.TealBorder
import com.example.appveterinaria.ui.theme.TealBrand
import com.example.appveterinaria.ui.theme.TealCyan
import com.example.appveterinaria.ui.theme.TealLight
import com.example.appveterinaria.ui.theme.TealSoftBg
import com.example.appveterinaria.ui.theme.TealTextDark
import com.example.appveterinaria.util.clp

@Composable
fun InicioScreen(
    totalClientes: Int,
    totalPedidos: Int,
    ultimoCliente: String,
    mascotas: List<Mascota>,
    consultas: List<Consulta>,
    pedidos: List<Pedido>,
    cargando: Boolean,
    onIrARegistro: () -> Unit,
    onIrACatalogo: () -> Unit,
    onRecargar: () -> Unit,
    modifier: Modifier = Modifier
) {
    var seccionActiva by rememberSaveable { mutableStateOf("mascotas") }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(CanvasCream),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header superior estilo Clínica Veterinaria
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .background(EmeraldBrand, RoundedCornerShape(14.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "🐾", fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "CLÍNICA",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = EmeraldTextDark,
                            letterSpacing = 0.8.sp
                        )
                        Text(
                            text = "VETERINARIA",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = EmeraldTextDark,
                            letterSpacing = 0.8.sp
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(SurfaceWhite, CircleShape)
                        .border(1.dp, BorderWarm, CircleShape)
                        .clickable { onRecargar() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "⟳", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = StoneDark)
                }
            }
        }

        // Título de sección y badge de activas
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Mis Citas y Resumen",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = StoneDark
                    )
                    Text(
                        text = "Gestión de dueños, mascotas y consultas",
                        fontSize = 12.sp,
                        color = StoneMuted,
                        fontWeight = FontWeight.Medium
                    )
                }
                Box(
                    modifier = Modifier
                        .background(
                            Brush.horizontalGradient(listOf(AmberSoftBg, AmberBorder)),
                            RoundedCornerShape(50)
                        )
                        .border(1.dp, AmberGold, RoundedCornerShape(50))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "● ${consultas.size} activas",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = AmberTextDark
                    )
                }
            }
        }

        // Tarjeta de resumen dinámico del sistema
        item {
            ResumenSistemaCard(
                totalClientes = totalClientes,
                totalPedidos = totalPedidos,
                ultimoCliente = ultimoCliente
            )
        }

        // Barra de pestañas segmentadas (estilo pill oscuro activo)
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceTabTrack, RoundedCornerShape(16.dp))
                    .border(1.dp, BorderWarm, RoundedCornerShape(16.dp))
                    .padding(5.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                FiltroTab(
                    texto = "Mascotas (${mascotas.size})",
                    activo = seccionActiva == "mascotas",
                    onClick = { seccionActiva = "mascotas" },
                    modifier = Modifier.weight(1f)
                )
                FiltroTab(
                    texto = "Consultas (${consultas.size})",
                    activo = seccionActiva == "consultas",
                    onClick = { seccionActiva = "consultas" },
                    modifier = Modifier.weight(1f)
                )
                FiltroTab(
                    texto = "Pedidos (${pedidos.size})",
                    activo = seccionActiva == "pedidos",
                    onClick = { seccionActiva = "pedidos" },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Indicador de carga asíncrona
        if (cargando) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, BorderWarm, RoundedCornerShape(20.dp)),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceWhite)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            color = EmeraldBrand,
                            modifier = Modifier.size(24.dp),
                            strokeWidth = 3.dp
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Sincronizando fichas veterinarias...",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = StoneDark
                        )
                    }
                }
            }
        }

        // Listado dinámico según pestaña seleccionada
        when (seccionActiva) {
            "mascotas" -> {
                items(mascotas, key = { it.id }) { mascota ->
                    MascotaItemCard(mascota = mascota)
                }
            }
            "consultas" -> {
                items(consultas, key = { it.id }) { consulta ->
                    ConsultaItemCard(consulta = consulta)
                }
            }
            "pedidos" -> {
                items(pedidos, key = { it.id }) { pedido ->
                    PedidoItemCard(pedido = pedido)
                }
            }
        }

        // Tarjeta de recomendaciones (estilo ámbar cálido del diseño)
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, AmberBorder, RoundedCornerShape(18.dp)),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = AmberSoftBg)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .background(SurfaceWhite, CircleShape)
                            .border(1.dp, AmberGold, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "!", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold, color = AmberDeep)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Recomendaciones para tu visita:",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = StoneDark
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Recuerda traer la cartilla de vacunación y llevar a tus mascotas con correa o en transportín seguro.",
                            fontSize = 11.sp,
                            color = AmberTextDark,
                            lineHeight = 16.sp
                        )
                    }
                }
            }
        }

        // Botones CTA principales con degradado esmeralda
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            Brush.horizontalGradient(listOf(EmeraldDark, EmeraldBrand, TealBrand))
                        )
                        .clickable { onIrARegistro() }
                        .padding(vertical = 14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "+ Nuevo Cliente",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            Brush.horizontalGradient(listOf(AmberBrand, AmberDeep))
                        )
                        .clickable { onIrACatalogo() }
                        .padding(vertical = 14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "+ Nueva Cita / Pedido",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun FiltroTab(
    texto: String,
    activo: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(if (activo) StoneDark else Color.Transparent)
            .clickable { onClick() }
            .padding(vertical = 9.dp, horizontal = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = texto,
            fontSize = 11.sp,
            fontWeight = if (activo) FontWeight.ExtraBold else FontWeight.SemiBold,
            color = if (activo) SurfaceWhite else StoneMuted
        )
    }
}

@Composable
private fun MascotaItemCard(mascota: Mascota) {
    val esGato = mascota.especie.equals("Gato", ignoreCase = true)
    val accentColors = if (esGato) listOf(TealBrand, TealCyan, TealLight) else listOf(AmberBrand, AmberDeep, AmberGold)
    val avatarBg = if (esGato) TealBadgeBg else AmberSoftBg
    val badgeText = if (esGato) TealTextDark else AmberTextDark
    val borderColor = if (esGato) TealBorder else AmberBorder

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, borderColor, RoundedCornerShape(24.dp)),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .background(Brush.horizontalGradient(accentColors))
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(avatarBg, RoundedCornerShape(16.dp))
                            .border(1.dp, borderColor, RoundedCornerShape(16.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = if (esGato) "🐱" else "🐕", fontSize = 24.sp)
                            Text(
                                text = mascota.especie.uppercase(),
                                fontSize = 8.sp,
                                fontWeight = FontWeight.Black,
                                color = badgeText
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = mascota.nombre,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = StoneDark
                        )
                        Text(
                            text = "${mascota.edad} años · ${mascota.raza}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = StoneMuted
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Dueño: ${mascota.dueno.nombre}",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = badgeText
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SurfaceWarmLow, RoundedCornerShape(12.dp))
                        .border(1.dp, BorderWarm, RoundedCornerShape(12.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "📍 ${mascota.dueno.direccion}  •  📞 ${mascota.dueno.telefono}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = StoneDark
                    )
                }
            }
        }
    }
}

@Composable
private fun ConsultaItemCard(consulta: Consulta) {
    val esGato = consulta.mascota.especie.equals("Gato", ignoreCase = true)
    val accentColors = if (esGato) listOf(TealBrand, TealCyan, TealLight) else listOf(AmberBrand, AmberDeep, AmberGold)
    val boxBg = if (esGato) TealSoftBg else AmberCreamBg
    val borderColor = if (esGato) TealBorder else AmberBorder
    val pillBg = if (esGato) TealBrand else AmberBrand
    val titleColor = if (esGato) TealTextDark else AmberTextDark

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, borderColor, RoundedCornerShape(24.dp)),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .background(Brush.horizontalGradient(accentColors))
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .background(boxBg, RoundedCornerShape(50))
                            .border(1.dp, borderColor, RoundedCornerShape(50))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "✓ ${consulta.estado}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = titleColor
                        )
                    }
                    Box(
                        modifier = Modifier
                            .background(pillBg, RoundedCornerShape(10.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = consulta.costo.clp(),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "${consulta.mascota.nombre} · ${consulta.mascota.raza}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = StoneDark
                )
                Text(
                    text = consulta.motivo,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = titleColor
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(EmeraldMintBg, RoundedCornerShape(12.dp))
                        .border(1.dp, EmeraldSoftBg, RoundedCornerShape(12.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = consulta.veterinario,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = StoneDark
                        )
                        Text(
                            text = "Dueño: ${consulta.mascota.dueno.nombre}",
                            fontSize = 11.sp,
                            color = StoneMuted
                        )
                    }
                    Text(
                        text = "✓ Asignada",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = EmeraldTextDark
                    )
                }
            }
        }
    }
}

@Composable
private fun PedidoItemCard(pedido: Pedido) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, TealBorder, RoundedCornerShape(24.dp)),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .background(Brush.horizontalGradient(listOf(TealBrand, TealCyan, TealLight)))
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Pedido #${pedido.id.take(6).uppercase()}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = StoneDark
                    )
                    Box(
                        modifier = Modifier
                            .background(TealBrand, RoundedCornerShape(10.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = pedido.total.clp(),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Cliente: ${pedido.cliente.nombre}" +
                            (pedido.mascota?.let { " · Mascota: ${it.nombre}" } ?: ""),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = EmeraldTextDark
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Ítems (${pedido.productos.size}): ${pedido.productos.joinToString { it.nombre }}",
                    fontSize = 12.sp,
                    color = StoneMuted
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InicioPreview() {
    val clienteDemo = Cliente(
        nombre = "Carolina Delgado",
        telefono = "+56 9 8765 4321",
        correo = "carolina@vetcare.cl",
        direccion = "Av. Providencia 1420"
    )
    val mascotaDemo = Mascota(
        nombre = "Luna",
        especie = "Perro",
        raza = "Golden Retriever",
        edad = 3,
        dueno = clienteDemo
    )
    AppVeterinariaTheme {
        InicioScreen(
            totalClientes = 2,
            totalPedidos = 2,
            ultimoCliente = "Matías Rojas",
            mascotas = listOf(mascotaDemo),
            consultas = emptyList(),
            pedidos = emptyList(),
            cargando = false,
            onIrARegistro = {},
            onIrACatalogo = {},
            onRecargar = {}
        )
    }
}
