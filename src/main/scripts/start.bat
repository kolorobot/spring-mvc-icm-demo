@echo off

rem Setting this value to 'true' will cause that the database will be created and loaded with initial data.
set "DATASOURCE_POPULATE=true"
rem JDBC URL string used to create a connection.
set "DATASOURCE_URL=jdbc:sqlite:%cd%\database\icm.db
rem Required to run Tomcat
set "CATALINA_HOME=%cd%\tomcat"
rem Passing properties to VM
set "JAVA_OPTS=-DdataSource.populate=true -DdataSource.url=jdbc:sqlite:%cd%\database\icm.db"
rem Run Tomcat
call %CATALINA_HOME%\bin\startup.bat
