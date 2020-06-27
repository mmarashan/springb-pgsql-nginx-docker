FROM gradle
COPY settings.gradle build.gradle ./
COPY gradle ./gradle