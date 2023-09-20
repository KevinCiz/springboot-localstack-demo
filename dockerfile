FROM openjdk:17-jdk-alpine

ARG USER=app
ARG UID=1001
ARG GROUP=app
ARG GUID=1001

# Create user to run app as (instead of root)
RUN addgroup -S -g ${GUID} ${GROUP} && adduser -u ${UID} -S -G ${GROUP} ${USER}

# Use user "app"
USER app

COPY target/*.jar app.jar

ENTRYPOINT [ "java", "-jar", "/app.jar" ]