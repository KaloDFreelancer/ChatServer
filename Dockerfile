FROM openjdk:11
COPY ./build/libs/ChatServer-0.0.1-SNAPSHOT.jar app.jar
CMD ["java","-jar","app.jar"]