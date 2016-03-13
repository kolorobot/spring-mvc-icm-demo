@echo off

set DATASOURCE_POPULATE=false
set DATASOURCE_URL="jdbc:sqlite:%cd%\icm.db"
set SERVER_PORT=9998

java -DdataSource.populate=%DATASOURCE_POPULATE% -DdataSource.url=%DATASOURCE_URL% -Dserver.port=%SERVER_PORT% -jar ${artifactId}-${version}.war
