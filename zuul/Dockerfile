FROM openjdk:11

RUN apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 8B48AD6246925553 7638D0442B90D010 CBF8D6FD518E17E1
RUN apt-get update
RUN apt-get install -y maven

# Adding springboot-elk app to container
ADD . /usr/config-client
WORKDIR /usr/config-client
RUN ["mvn", "package"]
VOLUME /tmp
EXPOSE 7000

ENTRYPOINT ["java", "-jar", "target/zuul-0.0.1-SNAPSHOT.jar"]