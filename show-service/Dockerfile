FROM java:8-jre
MAINTAINER Marcio Manske <mmanske@dminc.com>

ADD ./target/show-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/show-service.jar"]

EXPOSE 6100