#Project Description 
- Marvel Codelab will expose API to add superhero name into database.

## Dev Environment Setup for OS X.
* Java 1.8.0_112 and above
```
$ brew cask install java
```

[To install java please refer](https://docs.oracle.com/javase/10/install/overview-jdk-10-and-jre-10-installation.htm)
* Gradle v5.3.1

[To install gradle please refer](https://gradle.org/install/)
 
# How to Use
1. Build project using make.sh
2. use command `docker-compose up -d`
3. Run migrations `./gradlew flywayMigrate`
4. login to database using container id.  `docker exec -it container_id bash
5. Hit client request using bloomrpc OR `client.sh`
6. Make sure data should save into superhero db.


## Build instructions
```
sh make.sh
```
## start server 
```
sh start_Server.sh
```  
## start client
```
sh start_client.sh
```