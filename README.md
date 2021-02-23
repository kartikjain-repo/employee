## Local Setup

Clone and open the project in your IDE.
Run the main class [EmployeeApplication] as spring boot application.
The Employee Service is now ready to receive API requests.



## Application.properties

There are three profiles defined dev and prod, with individual application properties defined.
Profile specific properties file is picked according to the active profile defined.



NOTE: For running with external database like mysql (dev profile runs with h2), update application-prod.properties file with the database details. With docker for running with external database like mysql the docker network would have to be configured so the docker container can communicate with the database.



## REST API Documentation for Products

The API Documentation can also be found at `http://localhost:8080/swagger-ui.html/  

#### Install and run using codebase

Run 
```bash
mvn clean install
```



To run test cases 
```bash
mvn test
```

Run Sonar docker image
```bash
docker run -d -p 9000:9000 sonarqube
```

To generate sonar report with default port and host (http://localhost:9000)
```bash
mvn test sonar:sonar -Dsonar.login=admin -Dsonar.password=pass
```

To generate sonar report with remote server
```bash
mvn sonar:sonar -Dsonar.host.url=http://<sonarqubeserver>:<port>
```

#### Docker
In the  project folder run 
```bash
mvn clean install
```
To build the image
```bash
docker build -t emp-app .
```
To run the image with providing the profile
```bash
docker run -d -p 8080:8080 --name employee-app emp-app  
```



				
	
		
		



