FROM maven:slim as maven-build
WORKDIR '/app'
COPY pom.xml pom.xml
RUN ["mvn", "dependency:resolve"]
COPY src src
RUN ["mvn", "clean", "install", "-Dmaven.test.skip=true"]


FROM adoptopenjdk:11-jre-hotspot as runenv
RUN mkdir /opt/app
COPY pom.xml pom.xml
COPY --from=maven-build /app/target/home-soft-backend-0.0.1-SNAPSHOT.jar /opt/app/japp.jar
CMD ["java", "-jar", "/opt/app/japp.jar"]

