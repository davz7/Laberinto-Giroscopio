package mx.edu.laberinto_giroscopio.data.model

data class ScoreDto(
    val id: Long? = null,
    val userId: Long? = null,      // Agregado: Necesario para ENVIAR al servidor
    val username: String? = null,  // Opcional: El servidor lo devuelve al LEER, pero no se envía al crear
    val score: Int
)