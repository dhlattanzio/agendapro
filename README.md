# AgendaPro challenge

Aplicación desarrollada en Java con Spring Boot que expone una API REST para la gestión de productos y consulta de estadísticas.

## Tecnologías utilizadas
___

- Java 21
- Spring Boot 3.3.2
- MySQL 8.0
- Kafka

## Como utilizar la API
___

La forma más fácil de correr el proyecto es utilizando Docker Compose.

Ejecutar el siguiente comando en la raíz del proyecto:
```bash
docker compose up
```

Luego de unos segundos se iniciaran los servicios:
- **Product API**: http://localhost:8080
- **MySQL**: localhost:33062
- **Kafka**: localhost:9092

## Seguridad
___

La API usa y requiere el método de autenticación **Basic Auth** para todos los endpoints.  
Las credenciales por defecto son:
- **Usuario**: admin
- **Password**: admin

O también:
- **Usuario**: guess
- **Password**: guess

Los usuarios están en memoria y, aunque tienen roles distintos, ambos pueden acceder a todos los endpoints.

## Endpoints
___

### Productos
- **GET /products**: Obtiene todos los productos. Opcionalmente se pueden filtrar por cualquiera de los campos de los productos. Cuenta con paginación y ordenamiento.
- **GET /products/{id}**: Obtiene un producto por ID
- **POST /products**: Crea un producto
- **PUT /products/{id}**: Actualiza un producto
- **DELETE /products/{id}**: Elimina un producto

### Estadísticas
- **GET /products/stats**: Obtiene las estadísticas

## Swagger
___

La API cuenta con documentación Swagger que se puede acceder en:  
http://localhost:8080/swagger-ui/index.html

## Colección de Postman
___

En la raíz del proyecto se encuentra una colección de Postman con ejemplos de uso de la API.

## Tests
___

Para correr los tests se puede ejecutar el siguiente comando en la raíz del proyecto:
```bash
mvn test
```