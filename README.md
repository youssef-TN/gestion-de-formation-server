# Prerequisites
	- JDK 21 (link: https://builds.openlogic.com/downloadJDK/openlogic-openjdk/21.0.6+7/openlogic-openjdk-21.0.6+7-windows-x64.zip)
	- Maven 3 (link: https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.tar.gz)
	- Set java and maven in envirement variables
	- Download mongodb server (link: https://www.mongodb.com/try/download/community)
	- Download MongoDB Compass (GUI) (link: https://www.mongodb.com/try/download/compass)
# After downloading this project (using VS code)
	1. mvn clean install (to install dependencies)
	2. mvn spring-boot:run (to run the application)

# Server info 
	-link: localhost:8080
	-mongodb compass connection string: mongodb://root:password@localhost:28017/gestion-de-formation?authSource=admin