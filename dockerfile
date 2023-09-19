FROM openjdk:17-jdk-alpine

# Create user to run app as (instead of root)
RUN addgroup -S app && adduser -S app -G app

# Use user "app"
USER app

COPY target/*.jar app.jar

ENTRYPOINT [ "java", "-jar", "/app.jar" ]