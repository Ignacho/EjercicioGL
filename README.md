# Configuración utilizada
- Java jdk1.8.0_202
- Gradle 6.8
- Spring Boot 2.5

# Configuración DB
- DB H2 (embebida)
- Driver Class (org.h2.Driver)
- JDBC URL (jdbc:h2:mem:testdb)
- User Name (sa)
- Password: () -> empty


# Configuración EjercicioGL
- Instalar en IDE lombok desde https://projectlombok.org/download
- Importar en IDE proyecto Gradle
- Importar collections (Ejercicio GL-BCI.postman_collection.json) desde Postman para probar endpoints
- El POST localhost:8080/authenticate retorna un JWT que es requerido para utilizar los demás endpoints agregándole en Postman en la sección Authoritation -> Type: Bearer Token, dicho JWT.
 
 # Endpoints y contratos
 - POST localhost:8080/authenticate
 ```json
 {
  "name": "Ignacio",
  "email": "ignachobarberis@gmail.com",
  "password": "Pk123",
  "phones": [
    {
      "number": "22315297071",
      "citycode": "MDP",
      "countrycode": "AR"
    }
  ]
}
```
- GET localhost:8080/users/all
- DELETE localhost:8080/users/del/2
- PUT localhost:8080/users/upd
 ```json
 {
  "name": "Ignacio",
  "email": "ignachobarberis@gmail.com",
  "password": "Pk123",
  "phones": [
    {
      "number": "22315297071",
      "citycode": "MDP",
      "countrycode": "AR"
    }
  ]
}
```
