# reading-is-good

This project is using several technologies:

* Spring-boot
* Hibernate
* Spring-security
* H2 DB
* Swagger2

## Documentation
### swagger
```
http://localhost:8035/swagger-ui.html
```  
### Postman

Postman request collection file is in the following directory: 

**/src/main/resources.**

## Usage

#### docker
```
docker build -f Dockerfile -t reading-is-good-api .  
docker run -p 8081:8081 reading-is-good-api  
```  

First of all,the user has to get bearer token with username and password.

username: **admin** password: **admin**



### h-2 console link

```
http://localhost:8081/h2-console
```

### Bearer token link

```
http://localhost:8081/api/token
```
