# Api Web Services Meli

[![Build Status](https://travis-ci.org/codecentric/springboot-sample-app.svg?branch=master)](https://travis-ci.org/codecentric/springboot-sample-app)
[![Coverage Status](https://coveralls.io/repos/github/codecentric/springboot-sample-app/badge.svg?branch=master)](https://coveralls.io/github/codecentric/springboot-sample-app?branch=master)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Aplicación Back End con [Spring Boot](http://projects.spring.io/spring-boot/) api rest.

## Requerimientos

Para construir y ejecutar la aplicación necesita:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Ejecutando aplicación localmente

Hay varias formas de ejecutar una aplicación Spring Boot en su máquina local. Una forma es ejecutar el método `main` que se encuentra en la ruta `com.api.meli.apimeli.ApiMeliApplication` desde su IDE.

Alternativamente, puede usar el [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) asi:

```shell
./mvnw.cmd spring-boot:run
```

## Web services expuestos

Servicios creados:

* https://app-meli-back-end.herokuapp.com/api/listar : servicio que lista los item

## Consumiendo web services desde terminal linux
```shell
curl https://git-hub-api-rest.herokuapp.com/api/nivel1
```
```shell
curl https://git-hub-api-rest.herokuapp.com/api/coupon
```

## Copyright

<div>Juan Camilo Rodriguez Diaz</div>
<div>Ingeniero de sistemas y computación</div>
<div>Especialista en base de datos</div>
