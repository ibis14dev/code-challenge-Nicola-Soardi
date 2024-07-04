# code-challenge-Nicola-Soardi

Questo progetto è una RESTful API per la gestione delle prenotazioni di un ristorante.

## Indice

1. [Tecnologie utilizzate](#tecnologie-utilizzate)
2. [Prerequisiti](#prerequisiti)
3. [Installazione](#installazione)
4. [Esecuzione](#esecuzione)
5. [Endpoint dell'API](#endpoint-dellapi)
6. [Accesso al Database e alla Documentazione](#accesso-al-database-e-alla-documentazione)

## Tecnologie utilizzate

- [Java 17]
- [Spring Boot 3.3.1]
- [H2 Database 1.4.200]
- [Docker 20.10.7]
- [Apache Maven 3.9.2]

## Prerequisiti

Assicurati di avere installato i seguenti strumenti:

- [Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3.9.2](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/products/docker-desktop)

## Installazione

1. Clona il repository:
    ```bash
    git clone https://github.com/ibis14dev/code-challenge-Nicola-Soardi.git
    cd code-challenge-Nicola-Soardi
    ```

2. Compila il progetto con Maven (from Windows):
    ```bash
    mvnw.cmd clean package 
    ```

## Esecuzione

### Utilizzo di Docker

1. Assicurati di avere il file `Dockerfile` nella directory principale del progetto con il seguente contenuto:
    ```dockerfile
    FROM openjdk:17-jdk-slim
    VOLUME /tmp
    COPY target/code-challenge-Nicola-Soardi-0.0.1-SNAPSHOT.jar app.jar
    ENTRYPOINT ["java","-jar","/app.jar"]
    ```

2. Assicurati di avere il file `docker-compose.yml` nella directory principale del progetto con il seguente contenuto:
    ```yaml
    version: '3.8'
    services:
      app:
        build: .
        ports:
          - "8080:8080"
        environment:
          SPRING_H2_CONSOLE_ENABLED: true
          SPRING_H2_CONSOLE_PATH: /h2
          SPRING_DATASOURCE_URL: jdbc:h2:mem:code-challenge-Nicola-Soardi
          SPRING_DATASOURCE_USERNAME: ns
          SPRING_DATASOURCE_PASSWORD: code-challenge
          SPRING_JPA_HIBERNATE_DDL_AUTO: update
          SPRING_DATASOURCE_HIKARI_DATA_SOURCE_PROPERTIES_WEB_ALLOW_OTHERS: true
    ```

3. Esegui il comando per avviare i servizi definiti nel `docker-compose.yml`:
    ```bash
    docker-compose up --build
    ```

3. L'applicazione sarà disponibile su `http://localhost:8080`.

### Senza Docker

1. Esegui l'applicazione Spring Boot:
    ```bash
    mvn spring-boot:run
    ```

## Endpoint dell'API

### Clienti

- **GET /client/{id}** - Recupera un cliente dato il suo ID
   - **Risposta**: `200 OK` - Ritorna il cliente

- **POST /client** - Crea un nuovo cliente
   - **Risposta**: `201 Created` - Cliente creato con successo

- **DELETE /client/{id}** - Elimina un cliente dato il suo ID
   - **Risposta**: `204 No Content` - Cliente eliminato con successo

### Prenotazioni

- **GET /reservation/{startdate}/{enddate}** - Recupera prenotazioni date una data di inizio e una data di fine
   - **Risposta**: `200 OK` - Ritorna le prenotazioni

- **POST /reservation/client/{clientId}** - Crea una nuova prenotazione per un cliente specifico
   - **Risposta**: `201 Created` - Prenotazione creata con successo

- **DELETE /reservation/{id}** - Elimina una prenotazione dato il suo ID
   - **Risposta**: `204 No Content` - Prenotazione eliminata con successo

## Accesso al Database e alla Documentazione

- Per visualizzare il database H2, visita: [http://localhost:8080/h2](http://localhost:8080/h2)
    - Utilizza le seguenti credenziali:
        - **JDBC URL**: `jdbc:h2:mem:code-challenge-Nicola-Soardi`
        - **User Name**: `ns`
        - **Password**: `code-challenge`

- Per visualizzare la documentazione dell'API generata da Swagger, visita: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

