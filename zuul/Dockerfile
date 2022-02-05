FROM openjdk:11

RUN apt-get update
RUN apt-get install -y maven

# Adding springboot-elk app to container
ADD . /usr/config-client
WORKDIR /usr/config-client
RUN ["mvn", "package"]
VOLUME /tmp

#ENTRYPOINT ["java","-cp","app:app/lib/*","-Dspring.profiles.active=docker","com.chalvare.desmohispania.DesmohispaniaApplication.java"]
ENTRYPOINT ["java", "-jar", "target/zuul-0.0.1-SNAPSHOT.jar"]