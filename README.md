# Baozi Store API

REST API for a small Chinese-bun shop (clients, products, orders).
Java · Spring Boot · Spring Data JPA · H2 / MySQL · Postman

## Requirements

- JDK 25
- Git
- (Optional) MariaDB/MySQL — only if you don't want the default H2 database
- Maven is **not** needed: the project ships the Maven Wrapper (`./mvnw`)

## Installation

```bash
git clone https://github.com/lucas-rollin/baozi-store.git
cd baozi-store
./mvnw clean install -DskipTests
```

## Running

Default (H2 file database, no setup):

```bash
./mvnw spring-boot:run
```

The API runs at `http://localhost:8080`. H2 console: `http://localhost:8080/h2-console`
(JDBC URL `jdbc:h2:file:./data/baozi`, user `sa`, empty password).

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
