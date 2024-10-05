# Étape 1 : Construction
FROM maven:3.8.6-openjdk-17-slim AS build

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier pom.xml et les sources
COPY pom.xml .
COPY src ./src

# Construire le projet
RUN mvn clean package -DskipTests

# Étape 2 : Exécution
FROM openjdk:17-jdk-alpine

# Définir le répertoire de travail
WORKDIR /app

# Copier le JAR généré dans l'image
COPY --from=build /app/target/*.jar /app/my-spring-app.jar

# Exposer le port sur lequel Spring Boot écoute
EXPOSE 8080

# Commande pour exécuter l'application Spring Boot
ENTRYPOINT ["java", "-jar", "/app/my-spring-app.jar"]

