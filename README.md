# Laberinto - Giroscopio
> **Proyecto Integrador — Desarrollo de Aplicaciones Móviles**  
> **Grupo:** 4°A  
> **Fecha de entrega:** 11 de diciembre


## 👥 Equipo de Desarrollo

| Nombre Completo | Rol / Actividades Principales | Usuario GitHub |
|-----------------|-------------------------------|----------------|
| **Davor Sadrak Barrera Briones** | API REST, Retrofit, CRUD de puntuaciones, pruebas | @davz7 |
| **Ana Paula González Contreras** | Autenticación, navegación, UI, MainActivity | @Anapau-15 |
| **Eduardo Francisco Velázquez Molina** | Sensores, laberinto, movimiento, niveles, lógica del juego | @EduardoFVM |


## 🎮 Descripción del Proyecto

**Laberinto con Giroscopio** es un videojuego móvil desarrollado en Android donde el jugador controla una esfera dentro de un laberinto utilizando el **giroscopio del dispositivo**.  
El movimiento físico del usuario se traduce en desplazamientos dentro del juego, ofreciendo una experiencia inmersiva y distinta a los controles táctiles tradicionales.

La aplicación también integra un sistema completo basado en **API REST + Retrofit**, que permite:

- Crear cuentas de usuario  
- Iniciar sesión  
- Registrar puntajes  
- Consultar y actualizar la tabla de puntuaciones  

**Objetivo:** demostrar el uso de arquitectura móvil moderna, sensores físicos, consumo de servicios web y persistencia de datos en una aplicación funcional.


## 🛠️ Stack Tecnológico

- **Lenguaje:** Kotlin  
- **UI:** Jetpack Compose  
- **Arquitectura:** MVVM  
- **Consumo de API REST:** Retrofit  
    - **GET:** `/users`, `/scores`  
    - **POST:** `/users`, `/scores`  
    - **PUT:** `/users/{id}`, `/scores/{id}`  
    - **DELETE:** `/users/{id}`, `/scores/{id}`  
- **Sensor Implementado:** **Giroscopio**  
    - Detecta rotación del teléfono en tiempo real  
    - Convierte variaciones en los ejes X y Y en desplazamientos dentro del laberinto  


## 📸 Capturas de Pantalla

| Pantalla de Inicio | Uso del Sensor |
|--------------------|----------------|
| <img src="https://github.com/davz7/Laberinto-Giroscopio/blob/master/Imagenes/login.png?raw=true" width="260"> | <img src="https://github.com/davz7/Laberinto-Giroscopio/blob/master/Imagenes/sensor.png?raw=true" width="260"> |


## 📦 Instalación y Releases

El archivo ejecutable **.apk** firmado se encuentra disponible en la sección **Releases** del repositorio.

**Para instalar:**

1. Accede a la sección **Releases** (o haz clic aquí una vez que agregues el enlace).  
2. Descarga la versión más reciente del archivo `.apk`.  
3. Instálalo en tu dispositivo Android (habilita *instalación desde orígenes desconocidos* si es necesario).


