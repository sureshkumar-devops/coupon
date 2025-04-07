FROM  openjdk:17-jdk-alpine
RUN addgroup coupongrp && adduser -S -G coupongrp couponuser 
USER couponuser
WORKDIR /couponapp
COPY /target/coupon-0.0.1-SNAPSHOT.jar /couponapp/coupon-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD [ "java", "-jar", "/couponapp/coupon-0.0.1-SNAPSHOT.jar" ]

