
## Documentacion

## Herramientas tecnicas

    - Java JDK: 21
    - Maven: 3.9.9
    - Spring boot: 3.5.0
    - Docker-compose: 3.9
    - Caffeine cache: 3.2.0
    - Openapi: 3
    - JUnit: 5

## Ejecución

El aplicativo spring boot se encuentra disponible como imagen publica en **Docker Hub**  bajo el tag:

    alejoicecream/tenpochallenge-app-image:0.0.1

Para su ejecución depende de algunos contenedores complementarios, por ello se ha creado un archivo **docker-compose** el cual contiene la definición de todas los contenedores necesario:

Ubicar el arcivo docker-compose en carpeta raiz del repositorio:

        cd docker/docker-compose.yml

Ejecutar mediante uso de docker compose:

        docker-compose.yml -p compose up -d


### Entorno local

Tener en cuenta que los contenedores locales deben ejecutarse antes que el aplicativo, ejecutando los siguientes comandos.

    cd code/boot/
    cd src/main/resources/compose
    docker-compose.yml -p compose up -d

Ejecución del aplicativo spring boot a travez de maven, ejecutando los siguientes comandos dentro del directorio raiz del proyecto:

    cd code/boot
    mvn clean package
    mvn spring-boot:run

## REST Api

El servicio cuenta con la exposicion de un API REST, que cuenta con varios endpoints dedicados al caclulo de porcentajes numericos y registro del historial de peticiones.

![image](https://github-production-user-asset-6210df.s3.amazonaws.com/31171283/449551740-ec2f20aa-91fa-4cbb-82d9-f40a3d402be1.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAVCODYLSA53PQK4ZA%2F20250531%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250531T140135Z&X-Amz-Expires=300&X-Amz-Signature=12c5e29c64c5f3f451e9b0923a1a2b72a02a51cd81d1a481d7f34eefac2b5bd9&X-Amz-SignedHeaders=host)

Esposible acceder a la documentación de las api, utilizando la interfaz generada por **openapi aidocs**, accediendo en entorno local a la ruta:

    http://localhost:8080/swagger-ui/index.html

Ademas en el directorio raiz del repositorio se incluye collección postman con los end poinst expuestos por el servicio, además de la api externa para obtener el porcentaje, ver archivo:

**Tenpo Challenge Backend.postman_collection**

### Estuctura

El proyecto **Spring Boot** se encuentra ubicado en la carpeta raiz del proyecto, en el directorio ***code*** 

El aplicativo cuenta con una estructura de microservicio exponiendo un API REST.

Implementando una estructura **multi-modular** la cual se compone de varios modulos Maven los cuales implementan un proyecto pattern en común. 
```text
code/
└── boot/
    ├── src/
        └── main/
            └── ChallengeApplication.java     --Main Class
        └── resources/
            └── application.yml
            └── compose/
                └── docker-compose.yml
├── api/
    └── controller/    --REST Api implementation
    └── resources/
        └── openapi/   --REST Api definition
└── domain/
    ├── src/          --All domain dto and use cases
└── application/
    ├── src/          --All use cases implementation
└── infraestructure/
    └── services/     --REST Client implementation
    └── repository/   --Database Persistence
```

todos los modulos deben agregarse como dependencia dentro del modulo **boot** el cual es el encargado de levantar la aplicación ya que contiene la clase principal, junto con los archivos de propiedades *application.yml* y configuración de beans.

#### Arquitectura

El servicio cuenta con un diseño de Arquitectura Hexagonal, la cual implica separar los objetos y logica de negocio de las demás capas del aplicativo.

![Logo](https://miro.medium.com/v2/resize:fit:4800/format:webp/1*yR4C1B-YfMh5zqpbHzTyag.png)

Esto permite desacoplar la logica de negocio de librerias o tecnologias externas. Como por ejemplo cambiar el motor de base de datos no implicaria modificar la logica de negocio, ya que esta se abstrae del origen de los datos, simplemente espera un dto de dominio con el dato final.


## Pruebas

### Ejecución de Pruebas
Es posible ejecutar las pruebas en entorno local valiendose de maven, ejecutando los siguientes comandos dentro del directorio raiz del proyecto:

    cd code/boot
    mvn test

**NOTA:** La ejecución de los contenedores docker necesarios para el correcto funcionamiento de las pruebas de integración se realizara automaticamente al ejecutar el comando maven.

### Pruebas Unitarias
Las pruebas unitarias se han realizado mediante el uso de dependencias Junit 5 y Mockito, para la simulación del comportamiento de dependencias externas, probar el funcionamiento de cada componente especifico.

### Pruebas de Integración
Las pruebas de integración se encuentran automatizadas, utilizando junit5 y spring boot text, para iniciar la aplicación dentro de un contexto local y controlado, las clases de pruebas de integración se encuentran dentro del modulo:

```text
code/
└── boot/
    ├── src/
        └── test/
            └── com.tenpo.challenge
                └── integration/
```
Las pruebas de integración utilizan contenedores docker locales para simular el consumo de servidores externos,
utilizando docker-compose como herramienta para definir los servicios y su configuración.

El contenedor local para simulación de un servicio rest se ha implementado utilziando la imagen *MockServer* con el fin de definir una respuesta especifica para cada petición,.

Los escenarios de pruebas de integración requieren cambiar el status de cada respuesta, utilizando como recuros el archivo **application-test.yml** para modificar la url del servicio externo para obtener porcentajes según cada prueba.
