package com.example.appveterinaria.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appveterinaria.ui.theme.AmberBorder
import com.example.appveterinaria.ui.theme.AmberBrand
import com.example.appveterinaria.ui.theme.AmberCreamBg
import com.example.appveterinaria.ui.theme.AmberDeep
import com.example.appveterinaria.ui.theme.AmberGold
import com.example.appveterinaria.ui.theme.AmberSoftBg
import com.example.appveterinaria.ui.theme.AmberTextDark
import com.example.appveterinaria.ui.theme.EmeraldBrand
import com.example.appveterinaria.ui.theme.EmeraldMintBg
import com.example.appveterinaria.ui.theme.EmeraldSoftBg
import com.example.appveterinaria.ui.theme.EmeraldTextDark
import com.example.appveterinaria.ui.theme.StoneDark
import com.example.appveterinaria.ui.theme.StoneMuted
import com.example.appveterinaria.ui.theme.SurfaceWhite
import com.example.appveterinaria.ui.theme.TealBadgeBg
import com.example.appveterinaria.ui.theme.TealBorder
import com.example.appveterinaria.ui.theme.TealSoftBg
import com.example.appveterinaria.ui.theme.TealTextDark

@Composable
fun ResumenSistemaCard(
    totalClientes: Int,
    totalPedidos: Int,
    ultimoCliente: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, AmberBorder, RoundedCornerShape(24.dp)),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Barra superior de acento cálido
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(AmberBrand, AmberDeep, AmberGold)
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .background(AmberSoftBg, RoundedCornerShape(50))
                            .border(1.dp, AmberBorder, RoundedCornerShape(50))
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(7.dp)
                                    .background(AmberBrand, CircleShape)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Resumen en tiempo real",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = AmberTextDark
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .background(EmeraldMintBg, RoundedCornerShape(50))
                            .border(1.dp, EmeraldSoftBg, RoundedCornerShape(50))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "✓ Activo",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = EmeraldTextDark
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(AmberCreamBg, RoundedCornerShape(16.dp))
                            .border(1.dp, AmberBorder, RoundedCornerShape(16.dp))
                            .padding(12.dp)
                    ) {
                        Column {
                            Text(
                                text = "CLIENTES",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = AmberTextDark
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "$totalClientes",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = StoneDark
                            )
                            Text(
                                text = "Dueños registrados",
                                fontSize = 10.sp,
                                color = StoneMuted
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(TealSoftBg, RoundedCornerShape(16.dp))
                            .border(1.dp, TealBorder, RoundedCornerShape(16.dp))
                            .padding(12.dp)
                    ) {
                        Column {
                            Text(
                                text = "PEDIDOS",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = TealTextDark
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "$totalPedidos",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = StoneDark
                            )
                            Text(
                                text = "Órdenes realizadas",
                                fontSize = 10.sp,
                                color = StoneMuted
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(EmeraldMintBg, RoundedCornerShape(14.dp))
                        .border(1.dp, TealBadgeBg, RoundedCornerShape(14.dp))
                        .padding(horizontal = 14.dp, vertical = 10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "ÚLTIMO CLIENTE REGISTRADO",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = EmeraldTextDark
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = ultimoCliente,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = StoneDark
                            )
                        }
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .background(EmeraldBrand, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "🐾", fontSize = 16.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Resumen: $totalClientes clientes registrados, $totalPedidos pedidos realizados. Último cliente registrado: $ultimoCliente.",
                    fontSize = 11.sp,
                    color = StoneMuted,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 16.sp
                )
            }
        }
    }
}
