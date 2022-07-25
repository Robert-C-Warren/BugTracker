FROM openjdk:18
ADD target/bugtracker.jar bugtracker.jar
ENTRYPOINT ["java","-jar","BugTracker.jar"]