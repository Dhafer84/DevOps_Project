pipeline {
    agent any

    environment {
        DOCKER_CREDENTIALS_ID = 'Docker' // ID des credentials configurés
        DOCKER_IMAGE = "uncledhafer/devopsproject"  // Nom de l'image sans version
    }

    stages {
        stage('Import Project from Git') {
            steps {
                git branch: 'main', url: 'https://github.com/Dhafer84/DevOps_Project.git'
                echo 'Téléchargement du projet...'
            }
        }

        stage('Extract Version from pom.xml') {
            steps {
                script {
                    // Extraire la version du pom.xml
                    env.VERSION = sh(script: "mvn help:evaluate -Dexpression=project.version -q -DforceStdout", returnStdout: true).trim()
                    echo "Version extraite : ${env.VERSION}"
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Créer une image Docker avec la version extraite
                    def dockerImage = "${DOCKER_IMAGE}:${env.VERSION}"
                    echo "Construction de l'image Docker version ${dockerImage}..."
                    sh "docker build --build-arg JAR_FILE=DevOps_Project-${env.VERSION}.jar -t ${dockerImage} -f dockerfile ."
                }
            }
        }

        stage('Check Built JAR') {
            steps {
                echo 'Vérification des fichiers dans le répertoire target...'
                sh 'ls -al target/' // Lister les fichiers dans le répertoire target
            }
        }

        stage('Test Docker Login') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: "${DOCKER_CREDENTIALS_ID}", usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        sh "echo \$DOCKER_PASS | docker login -u \$DOCKER_USER --password-stdin"
                        echo "Connexion Docker OK"
                    }
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    def dockerImage = "${DOCKER_IMAGE}:${env.VERSION}"
                    echo "Push de l'image Docker version ${dockerImage} vers Docker Hub..."
                    sh "docker push ${dockerImage}" // Pousse l'image versionnée vers Docker Hub
                }
            }
        }

       
        stage('Deploy Docker Containers') {
            steps {
                echo 'Mise à jour des services Docker avec docker-compose...'
                sh """
                sed -i 's|uncledhafer/devopsproject:.*|uncledhafer/devopsproject:${env.VERSION}|' docker-compose.yml
                docker-compose up -d --build
                """
            }
        }

        stage('Run Unit Tests') {
            steps {
                echo 'Exécution des tests unitaires...'
                sh 'mvn test'
            }
        }

        stage('Generate Test Report') {
            steps {
                echo 'Génération du rapport de tests...'
                sh 'mvn surefire-report:report'
            }
        }

        stage('Publish Test Report') {
            steps {
                publishHTML(target: [
                    reportName: 'Rapport de Tests',
                    reportDir: 'target/surefire-reports', 
                    reportFiles: 'surefire-report.html',
                    keepAll: true,
                    alwaysLinkToLastBuild: true
                ])
            }
        }
    }

    post {
        always {
            echo 'Pipeline terminé.'
            sh 'docker-compose down' // Arrêter et nettoyer les conteneurs après l'exécution
        }
        success {
            echo 'La construction a réussi.'
        }
        failure {
            echo 'La construction a échoué.'
        }
    }
}

