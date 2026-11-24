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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appveterinaria.data.model.Mascota
import com.example.appveterinaria.data.model.Producto
import com.example.appveterinaria.ui.theme.AmberBrand
import com.example.appveterinaria.ui.theme.AmberDeep
import com.example.appveterinaria.ui.theme.BorderWarm
import com.example.appveterinaria.ui.theme.CanvasCream
import com.example.appveterinaria.ui.theme.EmeraldBrand
import com.example.appveterinaria.ui.theme.EmeraldDark
import com.example.appveterinaria.ui.theme.EmeraldMintBg
import com.example.appveterinaria.ui.theme.EmeraldSoftBg
import com.example.appveterinaria.ui.theme.EmeraldTextDark
import com.example.appveterinaria.ui.theme.StoneDark
import com.example.appveterinaria.ui.theme.StoneMuted
import com.example.appveterinaria.ui.theme.SurfaceWhite
import com.example.appveterinaria.ui.theme.TealBadgeBg
import com.example.appveterinaria.ui.theme.TealBorder
import com.example.appveterinaria.ui.theme.TealBrand
import com.example.appveterinaria.ui.theme.TealSoftBg
import com.example.appveterinaria.ui.theme.TealTextDark
import com.example.appveterinaria.util.clp

@Composable
fun CatalogoScreen(
    totalClientes: Int,
    totalPedidos: Int,
    ultimoCliente: String,
    ultimaMascota: Mascota?,
    catalogo: List<Producto>,
    carrito: Map<Producto, Int>,
    totalCarrito: Double,
    cantidadCarrito: Int,
    cargando: Boolean,
    mensajeEstado: String,
    onAgregar: (Producto) -> Unit,
    onQuitar: (Producto) -> Unit,
    onVaciar: () -> Unit,
    onConfirmar: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(CanvasCream),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 14.dp, bottom = 104.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Servicios y Productos",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = StoneDark
            )
            Text(
                text = "Cliente: $ultimoCliente" + (ultimaMascota?.let { " · Mascota: ${it.nombre}" } ?: ""),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = EmeraldTextDark
            )
        }

        item {
            ResumenSistemaCard(
                totalClientes = totalClientes,
                totalPedidos = totalPedidos,
                ultimoCliente = ultimoCliente
            )
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, TealBorder, RoundedCornerShape(20.dp)),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = TealSoftBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "ORDEN ACTUAL ($cantidadCarrito ítems)",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = TealTextDark
                    )
                    Text(
                        text = "Total (IVA 19% incluido): ${totalCarrito.clp()}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = StoneDark
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1.5f)
                                .height(46.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(
                                    if (!cargando && cantidadCarrito > 0) {
                                        Brush.horizontalGradient(listOf(EmeraldDark, EmeraldBrand, TealBrand))
                                    } else {
                                        Brush.horizontalGradient(listOf(BorderWarm, BorderWarm))
                                    }
                                )
                                .clickable(enabled = !cargando && cantidadCarrito > 0) { onConfirmar() },
                            contentAlignment = Alignment.Center
                        ) {
                            if (cargando) {
                                CircularProgressIndicator(
                                    color = Color.White,
                                    modifier = Modifier.size(20.dp),
                                    strokeWidth = 2.dp
                                )
                            } else {
                                Text(
                                    text = "+ Confirmar Pedido",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = if (cantidadCarrito > 0) Color.White else StoneMuted
                                )
                            }
                        }

                        OutlinedButton(
                            onClick = onVaciar,
                            enabled = cantidadCarrito > 0,
                            modifier = Modifier
                                .weight(0.8f)
                                .height(46.dp),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Text(
                                text = "Vaciar",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = StoneDark
                            )
                        }
                    }
                }
            }
        }

        if (mensajeEstado.isNotBlank()) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, EmeraldSoftBg, RoundedCornerShape(14.dp)),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = EmeraldMintBg)
                ) {
                    Text(
                        text = mensajeEstado,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldTextDark,
                        modifier = Modifier.padding(14.dp)
                    )
                }
            }
        }

        items(catalogo, key = { it.id }) { producto ->
            ProductoItem(
                producto = producto,
                cantidad = carrito[producto] ?: 0,
                onAgregar = { onAgregar(producto) },
                onQuitar = { onQuitar(producto) }
            )
        }
    }
}

@Composable
fun ProductoItem(
    producto: Producto,
    cantidad: Int,
    onAgregar: () -> Unit,
    onQuitar: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, BorderWarm, RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .background(TealBadgeBg, RoundedCornerShape(50))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = producto.categoria.titulo.uppercase(),
                        fontSize = 9.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = TealTextDark
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = producto.nombre,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = StoneDark
                )
                Text(
                    text = producto.precio.clp(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = AmberDeep
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                if (cantidad > 0) {
                    OutlinedButton(
                        onClick = onQuitar,
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text("-", fontWeight = FontWeight.ExtraBold, color = StoneDark)
                    }
                    Text(
                        text = "x$cantidad",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = AmberBrand
                    )
                }
                Button(
                    onClick = onAgregar,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldBrand),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Agregar",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                }
            }
        }
    }
}
