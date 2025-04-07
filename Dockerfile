FROM  openjdk:17-jdk-alpine
RUN addgroup coupongrp && adduser -S -G coupongrp couponuser
USER couponuser
RUN mkdir couponapp
WORKDIR /couponapp
COPY /target/*.war /couponapp/*.war
EXPOSE 8080
CMD [ "java", "-jar", "/couponapp/*.war" ]

