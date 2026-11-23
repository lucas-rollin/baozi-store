# Baozi Store API

REST API for a small Chinese-bun shop (clients, products, orders).  
Coursework project for Web Back-End Development (UNINTER).

**Stack:** Java 25 · Spring Boot 4 · Spring Data JPA · H2 / MySQL · OpenAPI (Swagger UI)

## Requirements

- JDK 25
- Git
- (Optional) MySQL: only if you don't want the default H2 database

## Running

```bash
git clone https://github.com/lucas-rollin/baozi-store.git
cd baozi-store
./mvnw spring-boot:run    # On Windows: mvnw.cmd spring-boot:run
```

The API runs at `http://localhost:8080`:
- **Swagger UI:** `http://localhost:8080/docs`
- **H2 console:** `http://localhost:8080/h2-console` (JDBC URL `jdbc:h2:file:./data/baozi;AUTO_SERVER=TRUE`, user `sa`, empty password)

### With MySQL (Podman / Docker)

Start a MySQL container matching `src/main/resources/application-mysql.properties` (replace `podman` with `docker` if using Docker):

```bash
podman run -d --name baozi-mysql \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=baozi \
  -e MYSQL_USER=baozi \
  -e MYSQL_PASSWORD=baozi123 \
  -p 3306:3306 \
  -v baozi-mysql-data:/var/lib/mysql \
  docker.io/library/mysql:8
```

Then run the application with the `mysql` profile:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=mysql
```

## Endpoints

There are 3 resources: **clientes**, **produtos** and **pedidos**

| Method | Path | Description |
| --- | --- | --- |
| POST | `/{resource}` | Create |
| GET | `/{resource}` | List all |
| GET | `/{resource}/{id}` | Get by ID |
| DELETE | `/{resource}/{id}` | Delete |
| PUT | `/{resource}/{id}` | Update |

Interactive schemas and examples are available at `http://localhost:8080/docs`. Example `POST /pedidos` body:

```json
{
  "cliente": { "id": 1 },
  "produto": { "id": 1 },
  "quantidade": 3
}
```

## Structure

```text
src/main/java/io/github/lucas_rollin/baozistore
├── model        # JPA entities: Cliente, Produto, Pedido
├── repository   # Spring Data JPA interfaces
└── controller   # REST controllers
```
