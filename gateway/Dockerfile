FROM java:8-jre
MAINTAINER Marcio Manske <mmanske@dminc.com>

ADD ./target/gateway.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/gateway.jar"]

EXPOSE 4000