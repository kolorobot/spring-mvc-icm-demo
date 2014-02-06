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
* Download the source code as zip
  * (Optional) Use *git* client and clone the repository
* Unzip the source code

Database
--------

The project is based on SQLite database. By default, the database will be created in a user's home directory.
If you want to change the data source url, please add 'dataSource.url=<url>' system property (usually through -D JVM switch).

Database schema will be created and the database will be populated with some initial data.
This can be disabled (e.g. at next application deployment) with 'dataSource.populate=<boolean>' system property.

Run the project
---------------

Navigate to the project folder and run the following command:

	mvn tomcat7:run

Open the browser and navigate to:

    http://localhost:8080/icm

Deploying to Tomcat (Windows)
-----------------------------

- Create a war file with `mvn clean package`
- Somewhere on disk create `icm` directory and `database` folder inside
- Download a Tomcat 7 server and extract to previously created directory
- Navigate to `CATALINA_HOME/webapps` folder and remove all folders and files
- Copy the war file to `CATALINA_HOME/webapps` folder and rename it to `ROOT.war`
- In the `icm` directory create a script to start the server (see `src/main/scripts/start.bat`)
- Browse to http://localhost:8080