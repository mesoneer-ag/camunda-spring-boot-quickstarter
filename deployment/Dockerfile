FROM openjdk:11-jre

RUN useradd -m appuser
USER appuser

WORKDIR /home/appuser

ENV JAR_NAME camunda-spring-boot-quickstarter-1.0.0.jar
ENV JAVA_OPTS -Xms128m -Xmx300m -Xss512k -XX:CICompilerCount=2 -XX:+UseContainerSupport

COPY build/libs/${JAR_NAME} ${JAR_NAME}

ENV PORT 8080
EXPOSE $PORT

CMD [ "sh", "-c", "java $JAVA_OPTS -jar ${JAR_NAME} --bind 0.0.0.0:$PORT" ]
