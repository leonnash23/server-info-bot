FROM openjdk:8u171-alpine3.7
RUN apk --no-cache add curl
COPY build\libs\server-tester-bot-*.jar server-tester-bot.jar
EXPOSE 80/tcp
CMD java ${JAVA_OPTS} -jar server-tester-bot.jar