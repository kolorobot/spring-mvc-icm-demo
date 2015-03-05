@echo off
rem JAVA_HOME property is required!

rem Setting this value to 'true' will cause that the database will be created and loaded with initial data.
rem Database can be also reset from application itself

set DATASOURCE_POPULATE=false

rem JDBC URL string used to create a connection.
rem set DATASOURCE_URL="jdbc:sqlite:%HOMEPATH%\icm.db"
set DATASOURCE_URL="jdbc:sqlite:%cd%\icm.db"

rem Change port value
set SERVER_PORT=8080

rem Passing properties to VM
rem set JAVA_OPTS=-DdataSource.populate=false -DdataSource.url=%DATASOURCE_URL%

java -DdataSource.populate=%DATASOURCE_POPULATE% -DdataSource.url=%DATASOURCE_URL% -Dserver.port=%SERVER_PORT% -jar icm-1.0.2.war
