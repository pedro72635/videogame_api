
# VideoGame API

API REST para gestionar un catálogo de videojuegos, con operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para administrar videojuegos en una base de datos MySQL. La API permite listar todos los videojuegos, obtener detalles de un videojuego específico, crear nuevos videojuegos, actualizar información existente y eliminar registros. La base de datos se inicializa automáticamente con datos de ejemplo al iniciar la aplicación.


## Tecnologias

**Java 17**: Lenguaje de programación principal.

**Spring Boot 3.3.2**: Framework para crear la API REST.

**MySQL**: Base de datos para almacenar los videojuegos.

**Hibernate/JPA**: Para la gestión de la persistencia de datos.

**Lombok**: Para reducir código repetitivo en las entidades.

**Maven**: Gestión de dependencias (aunque se ejecuta sin mvn en Visual Studio Code).

**Postman**: Herramienta para probar los endpoints.


## Endpoints
| Método  | Endpoint  | Descripción |
|:------------- |:---------------:| :-------------:|
| **GET**          | **/api/videogames**        | Lista todos los juegos        |
| **GET**        | **/api/videogames/{id}**          | Obtiene un videojuego por su ID.        |
| **POST**         | **/api/videogames**          | Crea un videojuego por su ID.        |
| **PUT**          | **/api/videogames**        |  Actualiza un videojuego existente.        |
| **DELETE**        | **/api/videogames/{id}**          | Elimina un videojuego por su ID.        |

## Requisitos
* **Java 17**

* **MySQL Server** 

* **Postman**

* **Git**
## Instalación
**1. Clona el repositorio:**
```
git clone https://github.com/pedro72635/videogame-api.git
cd videogame-api
```
**2. Configura MySQL:**

* Crea la base de datos videogame_db:
```
CREATE DATABASE videogame_db;
```
* **Actualiza el archivo applicaction.properties con tus credenciales de MySQL**
```
spring.datasource.url=jdbc:mysql://localhost:3306/videogame_db
spring.datasource.username=root
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.sql.init.mode=always
```
**3.Ejecución**

Ejecuta `src/main/java/com/pedro72635/videogameapi/VideogameApiApplication.java` para iniciar el programa.



## Datos iniciales

El programa se incializa automaticamente al iniciar la aplicación gracias al archivo `src/main/resources/data.sql`  .

| Título                                    | Género           | Plataforma         | Año  | Desarrollador       |
|-------------------------------------------|------------------|--------------------|------|---------------------|
| The Legend of Zelda: Breath of the Wild   | Action-Adventure | Nintendo Switch    | 2017 | Nintendo            |
| The Witcher 3: Wild Hunt                  | RPG              | PC                 | 2015 | CD Projekt Red      |
| Super Mario Odyssey                       | Platformer       | Nintendo Switch    | 2017 | Nintendo            |
| Elden Ring                                | Action-RPG       | PS5                | 2022 | FromSoftware        |
| Minecraft                                 | Sandbox          | Multiplatform      | 2011 | Mojang Studios      |

Estos datos se insertan en la tabla `video_game` en la base de datos `videogame_db`.

## Pruebas con Postman
1. **Configura Postman**:
   - Crea un entorno llamado `VideoGame API`:
     - Variable: `baseUrl`, Valor: `http://localhost:8080`.
   - Usa el encabezado `Content-Type: application/json` para solicitudes `POST` y `PUT`.

2. **Prueba los endpoints**:
   - **GET /api/videogames** (Listar todos los videojuegos):
     - URL: `{{baseUrl}}/api/videogames`
     - Respuesta esperada (HTTP 200):
       ```json
       [
           {
               "id": 1,
               "title": "The Legend of Zelda: Breath of the Wild",
               "genre": "Action-Adventure",
               "platform": "Nintendo Switch",
               "releaseYear": 2017,
               "developer": "Nintendo"
           },
           ...
       ]
       ```

   - **GET /api/videogames/{id}** (Obtener un videojuego por ID):
     - URL: `{{baseUrl}}/api/videogames/1`
     - Respuesta esperada (HTTP 200):
       ```json
       {
           "id": 1,
           "title": "The Legend of Zelda: Breath of the Wild",
           "genre": "Action-Adventure",
           "platform": "Nintendo Switch",
           "releaseYear": 2017,
           "developer": "Nintendo"
       }
       ```

   - **POST /api/videogames** (Crear un videojuego):
     - URL: `{{baseUrl}}/api/videogames`
     - Cuerpo (Body, JSON):
       ```json
       {
           "title": "Cyberpunk 2077",
           "genre": "RPG",
           "platform": "PS5",
           "releaseYear": 2020,
           "developer": "CD Projekt Red"
       }
       ```
     - Respuesta esperada (HTTP 200):
       ```json
       {
           "id": 6,
           "title": "Cyberpunk 2077",
           "genre": "RPG",
           "platform": "PS5",
           "releaseYear": 2020,
           "developer": "CD Projekt Red"
       }
       ```

   - **PUT /api/videogames/{id}** (Actualizar un videojuego):
     - URL: `{{baseUrl}}/api/videogames/1`
     - Cuerpo (Body, JSON):
       ```json
       {
           "title": "The Legend of Zelda: Breath of the Wild",
           "genre": "Action-Adventure",
           "platform": "Nintendo Switch",
           "releaseYear": 2017,
           "developer": "Nintendo EPD"
       }
       ```
     - Respuesta esperada (HTTP 200):
       ```json
       {
           "id": 1,
           "title": "The Legend of Zelda: Breath of the Wild",
           "genre": "Action-Adventure",
           "platform": "Nintendo Switch",
           "releaseYear": 2017,
           "developer": "Nintendo EPD"
       }
       ```

   - **DELETE /api/videogames/{id}** (Eliminar un videojuego):
     - URL: `{{baseUrl}}/api/videogames/1`
     - Respuesta esperada: HTTP 204 No Content.

3. **Verifica los cambios en MySQL**:
   - Conecta a MySQL (Workbench o terminal):
     ```sql
     USE videogame_db;
     SELECT * FROM video_game;
     ```