Spring MVC ICM Demo Application
=========================================

Summary
-------

The project is a demo project for QA students.

Installation
------------

* Install Java environment (JDK 1.8) - http://www.oracle.com/technetwork/java/javase/downloads/index.html
  * Don't forget to set JAVA_HOME environment variable after installation
* Install and configure Maven3 - http://maven.apache.org/download.cgi
* Download the source code as zip
  * (Optional) Use *git* client and clone the repository
* Unzip the source code

Database
--------

The project is based on SQLite database. By default, the database will be created in a user's home directory.
If you want to change the data source url, please add 'dataSource.url=<url>' system property (usually through -D JVM switch).

Database schema will be created and the database will be populated with some initial data.
This can be disabled (e.g. at next application deployment) with 'dataSource.populate=<boolean>' system property.

In addition, setup endpoint was created `/setup`. It allows re-setting the database at any time

Run the project
---------------

Navigate to the project folder and run the following command:

	mvn clean spring-boot:run

Open the browser and navigate to:

    http://localhost:8080

Package the project
-------------------

    mvn clean package spring-boot:repackage