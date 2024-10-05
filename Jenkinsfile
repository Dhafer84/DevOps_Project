pipeline {
    agent any

    stages {
        stage('Import Project from Git') {
            steps {
                // Cloner le projet depuis un dépôt Git
                git branch: 'main', url: 'https://github.com/Dhafer84/DevOps_Project.git'
                echo 'Téléchargement du projet...'
            }
        }

        stage('Build Project') {
            steps {
                echo 'Construction du projet...'
                sh 'ls'
                sh 'mvn package'
            }
        }

        stage('Test Project') {
            steps {
                echo 'Exécution des tests...'
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
                // Publier le rapport de tests
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
        }
        success {
            echo 'La construction a réussi.'
        }
        failure {
            echo 'La construction a échoué.'
        }
    }
}

