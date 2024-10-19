# Étape 1 : Image de build avec Maven et JDK 17
FROM maven:3.6.3-openjdk-17-slim AS build

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier pom.xml et télécharger les dépendances
COPY pom.xml /app/
RUN mvn dependency:go-offline -U

# Copier le code source
COPY src /app/src

# Construire le projet (compile et package en un seul appel)
RUN mvn clean package -Dmaven.repo.local=/root/.m2/repository

# Renommer le JAR à un nom fixe
RUN mv /app/target/*.jar /app/target/my-spring-app.jar

# Étape 2 : Image de production avec uniquement JDK 17
FROM openjdk:17-jdk-alpine

# Définir le répertoire de travail
WORKDIR /app

# Copier le JAR généré dans l'image de production
COPY --from=build /app/target/my-spring-app.jar /app/my-spring-app.jar

# Exposer le port utilisé par Spring Boot
EXPOSE 8082

# Commande pour exécuter l'application Spring Boot
ENTRYPOINT ["java", "-jar", "/app/my-spring-app.jar"]

