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
 
  
## Build instructions
  ```
  $ ./gradlew clean build
```
## Test instructions
```
$ ./gradlew clean test
```

## Code complexity
```
$ ./gradlew clean build check
```

## Code Coverage instructions
```
$ ./gradlew clean build jacocoTestReport
```