
# RUNNING IN DOCKER

**Attention** - *Commands mentioned in this document follow multi-line convention of linux. If you are going to run these commands on a windows commands prompt replace the break characters `\` with `^` in an editor before executing.*

1. Setup a remote git location as configuration source for config service.
2. A representative set is provided at [this location](https://github.ibm.com/chaudhary-amit/ms-boot-camp-docker-config). You must fork the repository for your own use.
3. Deploy Zipkin and RabbitMQ containers by following instructions in [RabbitMQZipkinDockerInstallationInstructions.docx](RabbitMQZipkinDockerInstallationInstructions.docx)
3. The provided configuration requires some environment variable to be set on docker which are mentioned in relevant sections.

### Steps
*This document uses `docker run` to deploy application to docker environment.*
##### 1. Login to docker hub - provide user name and docker hub token
```bash
docker login
```
##### 2. Create a bridge network
```bash
docker network create bootcamp_nw
```
##### 3. Deploy MYSQL database
```bash
docker run  -d --name db1 \
        --network bootcamp_nw \
        -e MYSQL_ROOT_PASSWORD=passw0rd \
        -e MYSQL_DATABASE=training \
        -e MYSQL_USER=dbuser \
        -e MYSQL_PASSWORD=passw0rd \
        --tmpfs /var/lib/mysql:rw mysql
```

##### 4. Deploy Demo Application. The demo application only depends on MYSQL and can be deployed as this time.

*Following command assumes that your spring boot server port is 9012 for the lab1_demo app. Please verify the same and adjust port accordingly.*

Build the application by going to lab1_demo directory
```bash
mvn install -DskipTests
```

Build and push docker image (provide your docker hub id)
```bash
docker build . -t YOUR_DOCKER_HUB_ID/lab1_demo && docker push YOUR_DOCKER_HUB_ID/lab1_demo
```
Deploy to docker (provide your docker hub id)
``` bash
docker run -d --name lab1_demo \
        --network bootcamp_nw \
        -e db.url='jdbc:mysql://db1:3306/training' \
        -e db.user=dbuser \
        -e db.password=passw0rd \
        -p 9012:9012 \
        YOUR_DOCKER_HUB_ID/lab1_demo
```
Verify the deployment by opening following URL in browser:
`http://DOCKER_HOST_IP:9012/greetings/greet?name=Someone`. Post an employee record to service and check the response.

Sample post payload to create an employee.
```json
{
   "firstName":"Viswanath",
   "lastName":"Chautala",
   "department":"Microservice Deveopment",
   "experiance":2
}
```

##### 5. Deploy configuration service. Before deploying make sure you git repository is setup with required configurations.

*Following command assumes that your spring boot server port is 8091 for the lab2_config_server app. Please verify the same and adjust port accordingly. If you change the port, you need to make sure references from other apps are corrected.*

Build the application by going to lab2_config_server directory
```bash
mvn install -DskipTests
```

Build and push docker image (provide your docker hub id)
```bash
docker build . -t YOUR_DOCKER_HUB_ID/config-service && docker push YOUR_DOCKER_HUB_ID/config-service
```
Deploy to docker (provide your docker hub id)

*Replace the values marked in ALL CAPS as per your environment*
```bash
docker run -d --name config-service \
        --network bootcamp_nw \
        -e config.source=https://github.ibm.com/YOUR_GIT_NAME/ms-boot-camp-docker-config.git \
        -e git.user=YOUR_GIT_USERNAME \
        -e git.password=YOUR_GIT_ACCESS_TOKEN \
        -e rabbitmq.host=DOCKER_HOST_IP e.g 192.168.99.100 \
        -p 8091:8091 \
        YOUR_DOCKER_HUB_ID/config-service
```   
Verify the deployment by opening following URL in browser: `http://DOCKER_HOST_IP:8091/product-service/default`.

##### 6. Deploy discovery service.
*Following command assumes that your spring boot server port is 8761 for the lab4_discovery_service app. Please verify the same and adjust port accordingly. If you change the port, you need to make sure references from other apps are accordingly corrected.*

Build the application by going to lab4_discovery_service directory
```bash
mvn install -DskipTests
```

Build and push docker image
```bash
docker build . -t YOUR_DOCKER_HUB_ID/discovery-service && docker push YOUR_DOCKER_HUB_ID/discovery-service
```
Deploy to docker
```bash
docker run -d --name discovery-service \
        --network bootcamp_nw \
        -p 8761:8761 \
        YOUR_DOCKER_HUB_ID/discovery-service
```   
Verify the deployment by opening following URL in browser: `http://DOCKER_HOST_IP:8761/`.

##### 7. Deploy gateway service.
*Following command assumes that your spring boot server port is 9000 for the lab6_api_gateway app. Please verify the same and adjust port accordingly.*

Build the application by going to lab6_api_gateway directory
```bash
mvn install -DskipTests
```

Build and push docker image
```bash
docker build . -t YOUR_DOCKER_HUB_ID/gateway-service && docker push YOUR_DOCKER_HUB_ID/gateway-service
```
Deploy to docker
```bash
docker run -d --name gateway-service \
        --network bootcamp_nw \
        -e config.server.url=http://config-service:8091 \
        -e zipkin.url=DOCKER_HOST_ZIPKIN_URL e.g. http://192.168.99.100:9411/ \
        -e rabbitmq.host=DOCKER_HOST_IP e.g 192.168.99.100 \
        -p 9000:9000 \
        YOUR_DOCKER_HUB_ID/gateway-service
```   
Verify that the gateway service instance is registered in Eureka


##### 8. Deploy product service.
Build the application by going to lab3_product_service directory
```bash
mvn install -DskipTests
```

Build and push docker image
```bash
docker build . -t YOUR_DOCKER_HUB_ID/product-service && docker push YOUR_DOCKER_HUB_ID/product-service
```
Deploy to docker (*you can run multiple instances of product service by repeating the command*)
```bash
docker run -d --network bootcamp_nw \
        -e config.server.url=http://config-service:8091 \
        -e zipkin.url=DOCKER_HOST_ZIPKIN_URL e.g. http://192.168.99.100:9411/ \
        -e rabbitmq.host=DOCKER_HOST_IP e.g 192.168.99.100 \
        YOUR_DOCKER_HUB_ID/product-service
```   
Verify the deployment by checking logs using `docker ps` and check logs using `docker logs`

Verify that the instance(s) are registered in Eureka

Varify product service response by opening following URL in browser: `http://DOCKER_HOST_IP:9000/api/products`

##### 9. Deploy discount service. (*you can run multiple instances of discount service by repeating the command*)
Build the application by going to lab5_discount_service directory
```bash
mvn install -DskipTests
```

Build and push docker image
```bash
docker build . -t YOUR_DOCKER_HUB_ID/discount-service && docker push YOUR_DOCKER_HUB_ID/discount-service
```
Deploy to docker
```bash
docker run -d --network bootcamp_nw \
        -e config.server.url=http://config-service:8091 \
        -e zipkin.url=DOCKER_HOST_ZIPKIN_URL e.g. http://192.168.99.100:9411/ \
        -e rabbitmq.host=DOCKER_HOST_IP e.g 192.168.99.100 \
        YOUR_DOCKER_HUB_ID/discount-service
```   
Verify the deployment by checking logs using `docker ps` and check logs using `docker logs`

Verify that the instance(s) are registered in Eureka

Setup product data in database preferably using post calls on product service at: `http://DOCKER_HOST_IP:9000/api/products` and verify application use-cases. example URL: `http://DOCKER_HOST_IP:9000/v1/products/1`


Sample post payload to create a product
```json
{
   "productId":1,
   "productName":"SamsungLED6i",
   "description":"60 inch Samsung LED TV",
   "productCategory":"tv",
   "mrp":50450.00
}
```


##### 10. Deploy web-audit service. (optional)
Build the application by going to lab7_web_audit_app directory
```bash
mvn install -DskipTests
```

Build and push docker image
```bash
docker build . -t YOUR_DOCKER_HUB_ID/audit-service && docker push YOUR_DOCKER_HUB_ID/audit-service
```
Deploy to docker
```bash
docker run -d --network bootcamp_nw \
        -e config.server.url=http://config-service:8091 \
        -e zipkin.url=DOCKER_HOST_ZIPKIN_URL e.g. http://192.168.99.100:9411/ \
        -e rabbitmq.host=DOCKER_HOST_IP e.g 192.168.99.100 \
        YOUR_DOCKER_HUB_ID/audit-service
```   
Verify the deployment by checking logs using `docker ps` and check logs using `docker logs`

Verify that the instance(s) are registered in Eureka

Post invocation of product-service -> discount-service check that audit_event table is being populated with discount events.
