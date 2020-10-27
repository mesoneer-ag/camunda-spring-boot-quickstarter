FROM openjdk:11-jre

RUN useradd -m appuser
USER appuser

WORKDIR /home/appuser

ENV JAVA_OPTS -Xms128m -Xmx300m -Xss512k -XX:CICompilerCount=2 -XX:+UseContainerSupport

COPY $JAR_NAME/*.jar $JAR_NAME.jar

ENV PORT 8080
EXPOSE $PORT

CMD [ "sh", "-c", "java $JAVA_OPTS -jar $JAR_NAME.jar --bind 0.0.0.0:$PORT" ]