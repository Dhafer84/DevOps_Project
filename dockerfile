# Utiliser une image Java officielle avec Maven
FROM maven:3.6.3-openjdk-17-slim AS build

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier pom.xml et le dossier source
COPY pom.xml /app/pom.xml
COPY src ./src

RUN mvn clean install -Dmaven.repo.local=/root/.m2/repository

# Construire le projet
RUN mvn clean package

# Étape de production
FROM openjdk:17-jdk-alpine

# Définir le répertoire de travail
WORKDIR /app

# Installer Maven dans l'image de production
RUN apk add --no-cache maven

# Copier le JAR généré dans l'image
COPY --from=build /app/target/DevOps_Project-1.0.jar /app/my-spring-app.jar

# Exposer le port sur lequel Spring Boot écoute
EXPOSE 8082

# Commande pour exécuter l'application Spring Boot
ENTRYPOINT ["java", "-jar", "/app/my-spring-app.jar"]

