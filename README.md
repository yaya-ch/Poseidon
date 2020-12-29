# Poseidon
_Poseidon_ is a secure web platform that allows users to subscribe and connect securely.
Its main objective is generating more transactions for investors who buy and sell fixed securities.

_Poseidon_ is also a Restful Api that is used by the web platform and can be used by other clients
that want to perform operations that resemble to the ones performed by the _Poseidon_ platform.


# Getting Started
These instructions will get you a copy of the project up and running on your local machine
for development and testing purposes.

# Prerequisites
What things you need to install and how will you install them:
- Java 1.8.
- Maven 3.6.3 (Maven is optional since the maven wrapper is present in the project).
- MySql 8.0.
- You can choose your favorite web browser to test the _Poseidon_ user interface.
    - Postman, or any similar software, can be used in order to interact with the _Poseidon_ api
      and perform all the `GET`, `POST`, `PUT` and `DELETE` operations.
# Installing
A step by step series of examples that tell you how to get a development environment:

1. Install Java:

https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

2. Install Maven (optional):

https://maven.apache.org/install.html

3. Install MySql:

https://dev.mysql.com/downloads/mysql/

4. Install Postman:

https://learning.postman.com/docs/getting-started/installation-and-updates/

After downloading the mysql 8.0 installer and installing it, you will be asked to configure the password
for the default root account. This code uses the default `root` account to connect, and the password used is
`rootroot`. If you add another user/credentials, make sure to change those present in the code base.
The credentials used by this application can be changed from the `application.properties` file.

# Note

You can choose to run the sql command **`CREATE DATABASE poseidon;`** in order to create the database.
The different tables and columns will be created automatically by the Java Persistence API during the first launch
of the application.

You can choose, also, to run the SQL script present in the **`data.sql`**(a file that can be found in the `docs/data.sql` folder)
in order to create the database, the different tables, as well as the different columns.

# Running App

After installing all the required softwares and creating the database,
you will be ready to import the code into your favorite IDE and run the PoseidonApplication.java
to start the application.

The application can be run, also, from the command line (for Windows users) or from the terminal
(for Linux and IOS users). To do so, you have to follow these steps :

- Open the terminal.
- Browse into the payMyBuddy folder.
- Run either `mvn package` command if you have maven installed on your machine
  or `./mvnw package` command if you do not have maven on your machine.
- After running one of the previous commands, a new folder named `target` will appear.
- Browse into this new folder and list its content. You will see a generated
  jar file named `Poseidon-0.0.1-SNAPSHOT.jar`.
- To execute this jar file, you need to run this command: `java -jar Poseidon-0.0.1-SNAPSHOT.jar`.
  Note that executing this jar will create the different tables and columns of the `poseidon` database.
- At this stage, the application is ready to be used. You can access the _Poseidon_
  home page via this endpoint `http://localhost:8080/`.

# Note

You can use also the `mvn spring-boot:run` command if you have maven on your machine
or `./mvnw spring-boot:run` to launch the application.

# The Poseidon API

A list of all the existing endpoint and supported operations of the _Poseidon_ api are listed bellow.

**GET**

You can find here the different endpoints that support `GET` operations:

`http://localhost:8080/api/bidList/findById/{id}` searches for a bidList by its id.

`http://localhost:8080/api/bidList/findAll` retrieves all the existing bidLists from the database.

*********
`http://localhost:8080/api/curvePoint/findById/{id}` searches for a curvePoint by its id.

`http://localhost:8080/api/curvePoint/findAll` retrieves all the existing curvePoints from the database.

*********
`http://localhost:8080/api/rating/findById/{id}` searches for a rating by its id.

`http://localhost:8080/api/rating/findAll` retrieves all the existing ratings from the database.

*********
`http://localhost:8080/api/ruleName/findById/{id}` searches for a ruleName by its id.

`http://localhost:8080/api/ruleName/findAll` retrieves all the existing ruleName from the database.

*********
`http://localhost:8080/api/trade/findById/{id}` searches for a trade by its id.

`http://localhost:8080/api/trade/findAll` retrieves all the existing trades from the database.

*********
`http://localhost:8080/api/user/findById/{id}` searches for a user by its id.

`http://localhost:8080/api/user/findAll` retrieves all the existing users from the database.

**POST**

You can find here the different endpoints that support `POST` operations:

`http://localhost:8080/api/bidList/add` creates a new BidList.

*********
`http://localhost:8080/api/curvePoint/add` creates a new curvePoint.

*********
`http://localhost:8080/api/rating/add` creates a new rating.

*********
`http://localhost:8080/api/ruleName/add` creates a new ruleName.

*********
`http://localhost:8080/api/trade/add` creates a new trade.

*********
`http://localhost:8080/api/user/add` creates a new user.

**PUT**

You can find here the different endpoints that support `PUT` operations:

`http://localhost:8080/api/bidList/update/{id}` update an existing bidList.

*********
`http://localhost:8080/api/curvePoint/update/{id}` update an existing curvePoint.

*********
`http://localhost:8080/api/rating/update/{id}` update an existing rating.

*********
`http://localhost:8080/api/ruleName/update/{id}` update an existing ruleName.

*********
`http://localhost:8080/api/trade/update/{id}` update an existing trade.

*********
`http://localhost:8080/api/user/update/{id}` update an existing user.

**DELETE**

You can find here the different endpoints that support `DELETE` operations:

`http://localhost:8080/api/bidList/delete/{id}` delete an existing bidList.

*********
`http://localhost:8080/api/curvePoint/delete/{id}` delete an existing curvePoint.

*********
`http://localhost:8080/api/rating/delete/{id}` delete an existing rating.

*********
`http://localhost:8080/api/ruleName/delete/{id}` delete an existing ruleName.

*********
`http://localhost:8080/api/trade/delete/{id}` delete an existing trade.

*********
`http://localhost:8080/api/user/delete/{id}` delete an existing user.

# Note

For more details on the _Poseidon_ Api, You can check the Swagger documentation after starting the application.
This documentation can be accessed via this Url `http://localhost:8080/swagger-ui.html`

# The web application:

The Poseidon web application can be accessed from the `http://localhost:8080/`

You have to log in first in order get access to most functionalities.
You can log in as a USER (users are authorized to only perform some action)
or as an Admin(admins have access to all the functionalities)
You can use the following credentials to log in to the application:
- Log in as an Admin: Username = _admin_ **|** Password = Test123@
- Log in as a User: Username = _user_ **|** Password = Test123@

#Note:

You must use the data provided in the `data.sql` file in order to create the previous users.
 Otherwise, you cannot use the previously provided credentials to log in to the application.

You can also insert them to your `poseidon` database by running the following sql script:

-`insert into user(full_name, username, password, role)
values("Administrator", "admin", "$2a$15$V6kP607Xgq4IdMsMADyqIOjHkjIAtV7yu/kcYZBTBQCvABklOtxRy", "ROLE_ADMIN");`

-`insert into user(full_name, username, password, role)
values("User", "user", "$2a$15$V6kP607Xgq4IdMsMADyqIOjHkjIAtV7yu/kcYZBTBQCvABklOtxRy", "ROLE_USER")`

# Testing

To run the unit tests, use either `mvn test` if you have maven on your machine
or `./mvnw test` if you do not have maven on your machine.

To execute both unit and integration tests use either `mvn verify` or `./mvnw verify`

To execute the integration tests, run either `mvn failsafe:integration-test` or `./mvnw failsafe:integration-test`

To get all the different reports in html format, you need to run either the `mvn site` or `./mvnw site` command.
This will get you a JaCoCo report, SureFire report, FailSafe report as well as a SpotBugs report.

You can find the different reports in the `target/site` directory.