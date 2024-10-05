# Utiliser une image Java officielle avec Maven
FROM openjdk:17-jdk-alpine AS build

# Installer Maven
RUN apk add --no-cache maven=3.6.3-r0

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier pom.xml et le dossier source
COPY pom.xml .
COPY src ./src

# Construire le projet
RUN mvn clean package

# Étape de production
FROM openjdk:17-jdk-alpine

# Définir le répertoire de travail
WORKDIR /app

# Copier le JAR généré dans l'image
COPY --from=build /app/target/my-spring-app.jar /app/my-spring-app.jar

# Exposer le port sur lequel Spring Boot écoute
EXPOSE 8080

# Commande pour exécuter l'application Spring Boot
ENTRYPOINT ["java", "-jar", "/app/my-spring-app.jar"]

