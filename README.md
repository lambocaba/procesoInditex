# Proceso de selección Inditex

## 📌 Descripción del Proyecto
Este proyecto ha sido desarrollado como parte de una prueba técnica. Se realizan pruebas automatizadas para la API de **PetStore** y una prueba de automatización de front utilizando **Spock, Geb y RestAssured**. Permite:
- Crear y recuperar usuarios mediante peticiones HTTP.
- Obtener la lista de mascotas vendidas.
- Contar los nombres de las mascotas repetidas.
- Ejecutar pruebas automatizadas con **Spock** en **Groovy**.


## 🛠 Requisitos Previos
Antes de ejecutar el proyecto, asegúrate de tener instalado lo siguiente:

- **Java 11**
- **Gradle 7.4**
- **Google Chrome**
- **IntelliJ IDEA** (Recomendado, pero puedes usar Eclipse u otro IDE compatible con Groovy y Spock)

## 📂 Estructura del Proyecto
El proyecto está organizado de la siguiente manera:

```
/procesoInditex
 ├── src/
 │   ├── main/
 │   │   ├── groovy/Main.groovy
 │   │   ├── java/
 │   │   │   ├── ApiClient.java
 │   │   │   ├── PetNameCounter.java
 │   │   │   ├── PetStoreService.java
 │   ├── test/
 │   │   ├── groovy/
 │   │   │   ├── GebPage.groovy
 │   │   │   ├── PetStoreApiSpec.groovy
 │   │   │   ├── TestFrontSpec.groovy  # Ejercicio 2
 │   │   │   ├── Pages/
 │   │   │   │   ├── GooglePage.groovy
 │   │   │   │   ├── WikipediaPage.groovy
 ├── build.gradle  # Configuración de Gradle
 ├── README.md     # Este archivo con las instrucciones
```

## 🚀 Instalación y Configuración

### 1️⃣ Clonar el repositorio
```sh
git clone https://github.com/lambocaba/procesoInditex.git
cd procesoInditex.git
```

### 2️⃣ Instalar dependencias
Ejecuta el siguiente comando en la terminal:
```sh
gradle clean build
```



## 🧪 Cómo Ejecutar los ejercicios

### Ejercicio 2
```sh
gradle test --tests TestFrontSpec
```
Si se ejecuta de esta forma es importante asegurar que el contexto esté activo en la pantalla del navegador. (clickandola cuando aparezca)
También se puede ejecutar desde el IDE.

### Ejercicio 3
```sh
  gradle test --tests PetstoreApiSpec
  ```
También se puede ejecutar desde el IDE.
Esto ejecutará tanto el caso de la creación del usuario mediante la API, como el listado de mascotas y sus nombres repetidos

### Ejecutar todas las pruebas con Gradle
```sh
gradle test
```

### Ejecutar pruebas específicas
```sh
.\gradlew test --tests "PetstoreApiSpec.Se crea un usuario correctamente y se devuelven sus datos"

```
## 📊 Ejemplo de salida real Ejercicio 2
La salida en consola será:
```json
ChromeDriver iniciado correctamente
El primer proceso automático ocurrió en: 1785
Captura de pantalla guardada como 'screenshot.png'
 Cerrando el navegador
```



## 📊 Ejemplo de salida real Ejercicio 3
La salida en consola será:
```json
Lista de mascotas vendidas:
{id: 156453.0, name: Updated Pet Name}
{id: 497046.0, name: Updated Pet Name}
{id: 246977.0, name: Updated Pet Name}
{id: 661323.0, name: Updated Pet Name}
{id: 9.223372036854776E18, name: Mia}
{id: 720503.0, name: Updated Pet Name}
{id: 391699.0, name: Updated Pet Name}
{id: 6012.0, name: bunny}
Conteo de nombres repetidos de mascotas vendidas:
Updated Pet Name: 10
Updated Integrity Test Pet: 2
doggie: 11
Special_char_owner_!@#$^&()`.testing: 2
```



