FROM openjdk:17
LABEL authors="lukebrandt"
COPY target/D387_sample_code-0.0.2-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]