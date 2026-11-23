package com.example.appveterinaria.util

object Validaciones {
    private val REGEX_CORREO = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    private val REGEX_NO_DIGITO = Regex("\\D")

    fun esCorreoValido(correo: String): Boolean =
        REGEX_CORREO.matches(correo.trim())

    fun esTelefonoValido(telefono: String): Boolean {
        val digitos = telefono.replace(REGEX_NO_DIGITO, "")
        return digitos.length in 8..12
    }

    fun validarRegistro(
        nombreDueno: String,
        correo: String,
        telefono: String,
        nombreMascota: String,
        edadTexto: String
    ): String? {
        if (nombreDueno.isBlank() || correo.isBlank() || telefono.isBlank() ||
            nombreMascota.isBlank() || edadTexto.isBlank()
        ) {
            return "Completa todos los campos obligatorios."
        }
        if (!esCorreoValido(correo)) {
            return "El correo no tiene un formato válido."
        }
        if (!esTelefonoValido(telefono)) {
            return "El teléfono debe tener entre 8 y 12 dígitos."
        }
        val edad = edadTexto.trim().toIntOrNull()
        if (edad == null || edad !in 0..35) {
            return "Ingresa una edad válida para la mascota (0 a 35 años)."
        }
        return null
    }
}