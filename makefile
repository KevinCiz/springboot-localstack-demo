start:
	docker-compose up -d && ./mvnw spring-boot:run -Dspring-boot.run.profiles=local 
	
up:
	docker-compose up -d