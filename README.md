# Laberinto-Giroscopio

> **Proyecto Integrador - Desarrollo de Aplicaciones Móviles**
>
> **Semestre:** [4°A]
> **Fecha de entrega:** 11 de Diciembre

---

## Equipo de Desarrollo

| Nombre Completo | Rol / Tareas Principales | Usuario GitHub |
| :--- | :--- | :--- |
| [Davor Sadrak Barrera Briones] | [API REST, Retrofit, CRUD de puntuaciones, pruebas] | @davz7|
| [Ana Paula Gonzalez Contreras] | [Autenticación, Navegación, UI, MainActivity] | @usuario2 |
| [Eduardo Francisco Velazquez Molina] | [Sensores, Laberinto, Movimiento, Niveles, Lógica del juego] | @EduardoFVM |

---

## Descripción del Proyecto

**¿Qué hace la aplicación?**
[La aplicación Laberinto con Giroscopio es un videojuego desarrollado para Android donde el jugador controla una esfera que se desplaza dentro de un laberinto. A diferencia de los controles tradicionales, la esfera se mueve usando el giroscopio del teléfono, permitiendo una experiencia interactiva y realista basada en el movimiento físico del dispositivo.

Además, la aplicación integra un sistema completo de autenticación y registro de puntuaciones usando Retrofit + API REST propia.
Cada usuario puede:

Crear una cuenta

Jugar varios niveles del laberinto

Guardar su puntuación

Consultar la tabla de puntuaciones]

**Objetivo:**
Demostrar la implementación de una arquitectura robusta en Android utilizando servicios web y hardware del dispositivo.

---

## Stack Tecnológico y Características

Este proyecto ha sido desarrollado siguiendo estrictamente los lineamientos de la materia:

* **Lenguaje:** Kotlin 100%.
* **Interfaz de Usuario:** Jetpack Compose.
* **Arquitectura:** MVVM (Model-View-ViewModel).
* **Conectividad (API REST):** Retrofit.
    * **GET:** [/users, /scores Obtiene usuarios y puntuaciones]
    * **POST:** [/users, /scores Registra usuarios y guarda puntuaciones]
    * **UPDATE:** [/users/{id}, /scores/{id} Actualiza registro]
    * **DELETE:** [/users/{id}, /scores/{id} Elimina registros]
* **Sensor Integrado:** [Giroscopio]
    * *Uso:* [El giroscopio detecta la rotación del teléfono en tiempo real para mover la esfera dentro del laberinto. Los movimientos X y Y se traducen a desplazamientos horizontales y verticales dentro del juego.]

---

## Capturas de Pantalla

[Coloca al menos 3 (investiga como agregarlas y se vean en GitHub)]

| Pantalla de Inicio | Operación CRUD | Uso del Sensor |
| :---: | :---: | :---: |
| ![Inicio](url_imagen) | ![CRUD](url_imagen) | ![Sensor](url_imagen) |

---

## Instalación y Releases

El ejecutable firmado (.apk) se encuentra disponible en la sección de **Releases** de este repositorio.

[Liga correctamente tu link de releases en la siguiente sección]

1.  Ve a la sección "Releases" (o haz clic [aquí](link_a_tus_releases)).
2.  Descarga el archivo `.apk` de la última versión.
3.  Instálalo en tu dispositivo Android (asegúrate de permitir la instalación de orígenes desconocidos).
