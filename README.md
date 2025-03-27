# Prerequisites
	- JDK 21 (link: https://builds.openlogic.com/downloadJDK/openlogic-openjdk/21.0.6+7/openlogic-openjdk-21.0.6+7-windows-x64.zip)
	- Maven 3 (link: https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.tar.gz)
	- Set java and maven in envirement variables

# After downloading this project (using VS code)
	1. mvn clean install (to install dependencies)
	2. docker compose up --build (to build an image in docker) 
	3. docker compose up -d (to run the image in the background)
	4. docker compose down (to close off the image)

# Server info 
	-link : localhost:8080