package com.example.appveterinaria.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appveterinaria.ui.theme.AppVeterinariaTheme
import com.example.appveterinaria.ui.theme.AmberBorder
import com.example.appveterinaria.ui.theme.AmberBrand
import com.example.appveterinaria.ui.theme.AmberDeep
import com.example.appveterinaria.ui.theme.AmberSoftBg
import com.example.appveterinaria.ui.theme.AmberTextDark
import com.example.appveterinaria.ui.theme.BorderWarm
import com.example.appveterinaria.ui.theme.CanvasCream
import com.example.appveterinaria.ui.theme.EmeraldBrand
import com.example.appveterinaria.ui.theme.EmeraldDark
import com.example.appveterinaria.ui.theme.EmeraldTextDark
import com.example.appveterinaria.ui.theme.StoneDark
import com.example.appveterinaria.ui.theme.StoneMuted
import com.example.appveterinaria.ui.theme.SurfaceTabTrack
import com.example.appveterinaria.ui.theme.SurfaceWarmLow
import com.example.appveterinaria.ui.theme.SurfaceWhite
import com.example.appveterinaria.ui.theme.TealBrand

@Composable
fun RegistroScreen(
    totalClientes: Int,
    totalPedidos: Int,
    ultimoCliente: String,
    mensajeEstado: String,
    onRegistrar: (
        nombreDueno: String,
        correo: String,
        telefono: String,
        direccion: String,
        nombreMascota: String,
        especie: String,
        raza: String,
        edad: String
    ) -> Boolean,
    onLimpiarMensaje: () -> Unit,
    onIrACatalogo: () -> Unit,
    modifier: Modifier = Modifier
) {
    var nombreDueno by rememberSaveable { mutableStateOf("") }
    var correo by rememberSaveable { mutableStateOf("") }
    var telefono by rememberSaveable { mutableStateOf("") }
    var direccion by rememberSaveable { mutableStateOf("") }

    var nombreMascota by rememberSaveable { mutableStateOf("") }
    var especie by rememberSaveable { mutableStateOf("Perro") }
    var raza by rememberSaveable { mutableStateOf("") }
    var edad by rememberSaveable { mutableStateOf("") }

    val coloresCampo = OutlinedTextFieldDefaults.colors(
        focusedTextColor = StoneDark,
        unfocusedTextColor = StoneDark,
        focusedContainerColor = SurfaceWhite,
        unfocusedContainerColor = SurfaceWarmLow,
        focusedBorderColor = EmeraldBrand,
        unfocusedBorderColor = BorderWarm,
        focusedLabelColor = EmeraldTextDark,
        unfocusedLabelColor = StoneMuted
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CanvasCream)
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp, top = 14.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "Nuevo Registro",
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            color = StoneDark
        )
        Text(
            text = "Ingresa los datos del dueño y su mascota para registrarlos en la clínica.",
            fontSize = 12.sp,
            color = StoneMuted
        )

        ResumenSistemaCard(
            totalClientes = totalClientes,
            totalPedidos = totalPedidos,
            ultimoCliente = ultimoCliente
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, BorderWarm, RoundedCornerShape(22.dp)),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "👤 DATOS DEL DUEÑO (CLIENTE)",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = EmeraldTextDark
                )

                OutlinedTextField(
                    value = nombreDueno,
                    onValueChange = { nombreDueno = it },
                    label = { Text("Nombre completo") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = coloresCampo,
                    singleLine = true
                )

                OutlinedTextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = { Text("Correo electrónico") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = coloresCampo,
                    singleLine = true
                )

                OutlinedTextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    label = { Text("Teléfono") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = coloresCampo,
                    singleLine = true
                )

                OutlinedTextField(
                    value = direccion,
                    onValueChange = { direccion = it },
                    label = { Text("Dirección") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = coloresCampo,
                    singleLine = true
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, BorderWarm, RoundedCornerShape(22.dp)),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "🐾 DATOS DE LA MASCOTA",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = AmberTextDark
                )

                OutlinedTextField(
                    value = nombreMascota,
                    onValueChange = { nombreMascota = it },
                    label = { Text("Nombre de la mascota") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = coloresCampo,
                    singleLine = true
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SurfaceTabTrack, RoundedCornerShape(14.dp))
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    listOf("Perro", "Gato", "Otro").forEach { opcion ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(10.dp))
                                .background(if (especie == opcion) StoneDark else Color.Transparent)
                                .clickable { especie = opcion }
                                .padding(vertical = 9.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = opcion,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (especie == opcion) SurfaceWhite else StoneMuted
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedTextField(
                        value = raza,
                        onValueChange = { raza = it },
                        label = { Text("Raza") },
                        modifier = Modifier.weight(1.4f),
                        shape = RoundedCornerShape(14.dp),
                        colors = coloresCampo,
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = edad,
                        onValueChange = { edad = it },
                        label = { Text("Edad") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(14.dp),
                        colors = coloresCampo,
                        singleLine = true
                    )
                }
            }
        }

        if (mensajeEstado.isNotBlank()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, AmberBorder, RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = AmberSoftBg)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = mensajeEstado,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = AmberTextDark
                    )
                    if (mensajeEstado.startsWith("Registrado:")) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = onIrACatalogo,
                            colors = ButtonDefaults.buttonColors(containerColor = AmberDeep),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "Ir a Servicios y Pedidos →",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = {
                    val ok = onRegistrar(
                        nombreDueno,
                        correo,
                        telefono,
                        direccion,
                        nombreMascota,
                        especie,
                        raza,
                        edad
                    )
                    if (ok) {
                        nombreDueno = ""
                        correo = ""
                        telefono = ""
                        direccion = ""
                        nombreMascota = ""
                        raza = ""
                        edad = ""
                    }
                },
                modifier = Modifier
                    .weight(1.4f)
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = EmeraldBrand)
            ) {
                Text(
                    text = "Registrar",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
            }

            OutlinedButton(
                onClick = {
                    nombreDueno = ""
                    correo = ""
                    telefono = ""
                    direccion = ""
                    nombreMascota = ""
                    raza = ""
                    edad = ""
                    onLimpiarMensaje()
                },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(
                    text = "Limpiar",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = StoneDark
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistroPreview() {
    AppVeterinariaTheme {
        RegistroScreen(
            totalClientes = 2,
            totalPedidos = 2,
            ultimoCliente = "Matías Rojas",
            mensajeEstado = "",
            onRegistrar = { _, _, _, _, _, _, _, _ -> true },
            onLimpiarMensaje = {},
            onIrACatalogo = {}
        )
    }
}
