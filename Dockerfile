# Build stage
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy everything into the build container
COPY . .

# Move into your Maven project directory to build it
WORKDIR /app/JavaConnector
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy everything from the build container
COPY --from=build /app /app

# Run the built jar (adjust jar name if needed)
ENTRYPOINT ["java", "-jar", "JavaConnector/target/JavaConnector.jar", "--cli"]