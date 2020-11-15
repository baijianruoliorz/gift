FROM maven:3.6.3-openjdk-8 AS MAVEN_BUILD
#把jar包靠上去,命名为app.jar
COPY *.jar /app.jar

CMD ["-----server.port=8003"]
#暴露你的端口
EXPOSE 8003

VOLUME /tmp
#执行jar命令,启动app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","jar","/app.jar"]
#部署到docker时,只需传Dockerfile和jar即可

