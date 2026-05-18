# Proyecto Rapicancha - Backend Microservicios

El sistema Rapicancha es una plataforma para la reserva y gestión de canchas deportivas. Este repositorio contiene el backend del sistema, el cual está construido utilizando una arquitectura de microservicios basada en Spring Boot 3 y Spring Cloud.

## Estructura del Proyecto

El backend está dividido en 6 servicios independientes:

*   **microservice.config**: Servidor de configuración centralizado (Spring Cloud Config). Corre en el puerto 8888.
*   **microservice.eureka**: Servidor de descubrimiento (Netflix Eureka). Corre en el puerto 8761.
*   **microservice.gateway**: API Gateway (Spring Cloud Gateway). Es el único punto de acceso para el frontend. Corre en el puerto 8080.
*   **microservice.auth**: Servicio de gestión de usuarios, perfiles y autenticación. Corre en el puerto 8081.
*   **microservice.booking**: Servicio encargado de gestionar las reservas y horarios disponibles. Corre en el puerto 8082.
*   **microservice.court**: Servicio encargado de administrar el catálogo de locales y canchas. Corre en el puerto 8083.

## Requisitos Previos

*   Java 21
*   Maven
*   PostgreSQL instalado localmente

## Instrucciones para Ejecución Local

### 1. Configuración de Base de Datos
Cada microservicio de negocio utiliza su propia base de datos independiente. Debes crear las siguientes bases de datos en tu servidor PostgreSQL local:

```sql
CREATE DATABASE db_rapicancha_auth;
CREATE DATABASE db_rapicancha_booking;
CREATE DATABASE db_rapicancha_court;
```

### 2. Variables de Entorno
El proyecto requiere inyectar las credenciales de la base de datos de PostgreSQL. Tienes dos opciones dependiendo de tu IDE:

**Opción A: Usar archivo `.env` (Ideal para VS Code)**
Copia el archivo `.env.template` de la raíz, renómbralo a `.env` y coloca ahí tus credenciales. Este archivo servirá para todos los microservicios:

```env
DB_USERNAME=tu_usuario
DB_PASSWORD=tu_contraseña
DB_URL_AUTH=jdbc:postgresql://localhost:5432/db_rapicancha_auth
DB_URL_BOOKING=jdbc:postgresql://localhost:5432/db_rapicancha_booking
DB_URL_COURT=jdbc:postgresql://localhost:5432/db_rapicancha_court
```

**Opción B: Nativamente en IntelliJ IDEA**
Ve a las opciones de ejecución (`Edit Configurations...`). En el campo **Environment variables**, pega la línea correspondiente para cada microservicio (ajustando tu usuario y clave):

Para `microservice.auth`:
```text
DB_USERNAME=tu_usuario;DB_PASSWORD=tu_contraseña;DB_URL_AUTH=jdbc:postgresql://localhost:5432/db_rapicancha_auth
```

Para `microservice.booking`:
```text
DB_USERNAME=tu_usuario;DB_PASSWORD=tu_contraseña;DB_URL_BOOKING=jdbc:postgresql://localhost:5432/db_rapicancha_booking
```

Para `microservice.court`:
```text
DB_USERNAME=tu_usuario;DB_PASSWORD=tu_contraseña;DB_URL_COURT=jdbc:postgresql://localhost:5432/db_rapicancha_court
```

### 3. Orden de Arranque
Para asegurar que los servicios se comuniquen y registren correctamente, debes iniciarlos en el siguiente orden, esperando que cada uno termine de arrancar antes de iniciar el siguiente:

1.  `microservice.config`
2.  `microservice.eureka`
3.  `microservice.gateway`
4.  `microservice.auth`
5.  `microservice.court`
6.  `microservice.booking`

Para verificar que todos los servicios han iniciado correctamente, ingresa a `http://localhost:8761` en tu navegador. Deberás ver registrados el Gateway y los 3 servicios de negocio.

Todas las peticiones desde el frontend o Postman deben dirigirse a la puerta de enlace en `http://localhost:8080`.
