# ForoHub

![Java](https://img.shields.io/badge/Java-21-007396?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0.0-brightgreen?logo=spring&logoColor=white)
![MariaDB](https://img.shields.io/badge/MariaDB-10.5-blue?logo=mariadb&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-yellow?logo=license&logoColor=white)

ForoHub es una API REST desarrollada con Spring Boot que permite gestionar tópicos y usuarios en un sistema de foro.

## Características

- **Gestión de tópicos**: Crear, leer, actualizar y eliminar (lógicamente) tópicos.
- **Autenticación con Spring Security**: Uso de JWT para la seguridad del API.
- **Conexión a base de datos MariaDB**.
- **Logs detallados**: Habilitados para Hibernate y Spring Security.
- **Configuración personalizada**: Variables de entorno para credenciales sensibles.

## Requisitos

- Java 17 o superior.
- MariaDB instalado y configurado.
- Variables de entorno para:
    - `DB_USER`: Usuario de la base de datos.
    - `DB_PASS`: Contraseña de la base de datos.
    - `JWT_SECRET`: Secreto para generar tokens JWT.



## Rutas disponibles

### Tópicos (`/topicos`)

- **POST** `/topicos`
    - Descripción: Crear un nuevo tópico.
    - Body esperado:
      ```json
      {
        "titulo": "string",
        "mensaje": "string",
        "curso": "string",
        "autor": 1
      }
      ```

- **GET** `/topicos`
    - Descripción: Listar los tópicos activos con paginación.
    - Parámetros opcionales:
        - `size`: Tamaño de la página (por defecto 5).
        - `page`: Número de página.

- **GET** `/topicos/{id}`
    - Descripción: Obtener detalles de un tópico por su ID.

- **PUT** `/topicos`
    - Descripción: Actualizar un tópico existente.
    - Body esperado:
      ```json
      {
        "id": 1,
        "titulo": "string",
        "mensaje": "string",
        "curso": "string"
      }
      ```

- **DELETE** `/topicos/{id}`
    - Descripción: Eliminar lógicamente un tópico por su ID.

## Licencia

Este proyecto está licenciado bajo la licencia MIT.

