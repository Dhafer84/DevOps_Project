# Étape 1 : Image de build avec Maven et JDK 17
FROM maven:3.6.3-openjdk-17-slim AS build

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier pom.xml et télécharger les dépendances
COPY pom.xml /app/
#COPY pom.xml .
RUN mvn dependency:go-offline

# Copier le code source
COPY src /app/src
#COPY src ./src

# Construire le projet (compile et package en un seul appel)
RUN mvn clean package -Dmaven.repo.local=/root/.m2/repository

# Étape 2 : Image de production avec uniquement JDK 17
FROM openjdk:17-jdk-alpine

# Définir le répertoire de travail
WORKDIR /app

# Copier le JAR généré dans l'image de production
COPY --from=build /app/target/DevOps_Project-1.0.jar /app/my-spring-app.jar

# Exposer le port utilisé par Spring Boot
EXPOSE 8082

# Commande pour exécuter l'application Spring Boot
ENTRYPOINT ["java", "-jar", "/app/my-spring-app.jar"]
