# Jullierme Barros - Schoology Test

## Project structure
    - api/schoology-test - A Java project for the task 1
    - ui/schoology-test  - A React project for the task 2

#### How to start?

    docker-compose up --build   (root folder)


    also

    api/schoology-test/docker-compose up --build   (just the backend)

    ui/schoology-test/docker-compose up --build   (just the fronted)

#### How to access?

[http://localhost:8081](http://localhost:8081)

#### How to access the API documentation?

[http://localhost:8080/api/doc](http://localhost:8080/api/doc)


#### API curl commnad

    curl -X GET "http://localhost:8080/country/find-by-name/brazil?size=7" -H "accept: */*"

######Note: To change the filter, remove the word brazil and use the same space.


#### About the Java project:

    - Using:
        - Java 8
        - Spring 5
        - JPA/Hibernate
        - MySql
        - JUnit 5
        - Mockito
        - Swagger
        - Docker
    - Running on 8080 port 
    - 100% test coverage

####About the React project:

    - Using:
        - React 16.9.12 (Last version in 2019-11-24)
        - Typescript 
        - Axios
        - Jest
        - Enzyme
        - ESlint
        - Docker
    - Running on 8081 port 
    - Good unit tests but needs more
