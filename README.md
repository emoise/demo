# Yonder technical assignment 

### Usage

#### Local startup
mvn spring-boot:run

Access http://localhost:8080/swagger-ui/index.html
#### Running in docker
mvn clean package

docker build . -t demo

docker run -p8080:8080 demo

Access http://localhost:8080/swagger-ui/index.html

