FROM java:8-jre
MAINTAINER Marcio Manske <mmanske@dminc.com>

ADD ./target/event-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/event-service.jar"]

EXPOSE 7100