@echo off
rem JAVA_HOME property is required
set JAVA_HOME="%cd%\jdk1.7.0_51_x86"
rem Setting this value to 'true' will cause that the database will be created and loaded with initial data.
set DATASOURCE_POPULATE=true
rem JDBC URL string used to create a connection.
set DATASOURCE_URL="jdbc:sqlite:%cd%\database\icm.db"
rem Required by Tomcat
set CATALINA_HOME=%cd%\tomcat
rem Passing properties to VM
set JAVA_OPTS=-DdataSource.populate=%DATASOURCE_POPULATE% -DdataSource.url=%DATASOURCE_URL%
rem Run Tomcat
call %CATALINA_HOME%\bin\startup.bat
