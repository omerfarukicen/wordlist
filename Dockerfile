FROM openjdk:17
COPY /target/wordlist*.jar /wordlist.jar
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}
ENTRYPOINT exec java  -jar /wordlist.jar


