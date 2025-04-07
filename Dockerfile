FROM  openjdk:17-jdk-alpine
RUN addgroup coupongrp && adduser -S -G coupongrp couponuser
USER couponuser
WORKDIR /couponapp
RUN chown -R couponuser:coupongrp /couponapp
COPY /target/*.war /couponapp/*.war
EXPOSE 8080
CMD [ "java", "-jar", "/couponapp/*.war" ]

