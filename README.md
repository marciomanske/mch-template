# mch-template

## To compile: 

mvn clean package

## To create images: 

docker-compose -f docker-compose.yml -f docker-compose.dev.yml up -d

## To list containers

docker ps

## To stop a container

docker stop <containerId>

## To delete a container

docker rm <containerId>

## To delete an image

docker rmi <image name>