Spring MVC ICM Demo Application
=========================================

Summary
-------
The project is a demo project for QA students.

Installation
------------

* Install Java environment (JDK 1.7) - http://www.oracle.com/technetwork/java/javase/downloads/index.html
  * Don't forget to set JAVA_HOME environment variable after installation
* Install and configure Maven3 - http://maven.apache.org/download.cgi
* Install MySQL 5 database - http://dev.mysql.com/downloads/
  * Create database using the following script: https://github.com/kolorobot/spring-mvc-icm-demo/blob/master/src/main/sql/mysql-create-database.sql
* Download the source code as zip
  * (Optional) Use *git* client and clone the repository
* Unzip the source code

Run the project
----------------

Navigate to the project folder and run the following command:

	mvn tomcat7:run

Open the browser and navigate to:

    http://localhost:8080/icm
