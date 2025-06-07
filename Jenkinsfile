pipeline {
    agent any
    environment {
        JAVA_HOME = 'usr/bin/java'
        MAVEN_HOME = '/opt/apache-maven-3.9.9/bin/mvn'
        PATH = "${env.PATH}:${JAVA_HOME}/bin:${MAVEN_HOME}:/usr/bin"
        //PYTHON_HOME = 'usr/bin/python3'
    }
    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'main', description: 'Branche Git à utiliser')
    }
    stages {
        stage('Checkout') {
            steps {
                //git url: 'https://github.com/gedegithub/maven-pipeline.git'
                git branch: "${params.BRANCH_NAME}", url: 'https://github.com/gedegithub/maven-pipeline.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Exemple') {
            steps {
                echo 'Bonjour depuis Jenkins'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
        stage('Analyse') {
            steps {
                sh 'mvn checkstyle:checkstyle'
                publishHTML(target: [
                    reportDir: 'target/site',
                    reportFiles: 'checkstyle.html',
                    reportName: 'Checkstyle',
                    allowMissing: false,
                    keepAll: true,
                    alwaysLinkToLastBuild: true
                ])
            }
        }
    }
}
