1 pipeline {
2   agent any
3   environment {
      JAVA_HOME = 'usr/bin/java'
      PATH = "${env.PATH}:${JAVA_HOME}/bin:/usr/bin"
4     //PYTHON_HOME = 'usr/bin/python3'
5   }
    parameters {
    string(name: 'BRANCH_NAME', defaultValue: 'main', description: 'Branche Git à utiliser')
    }
6   stages {
17     stage('Exemple') {
18       steps {
19         echo 'Bonjour depuis Jenkins'
20       }
21     }       
7     stage('Checkout') {
8       steps {
9         //git url: 'https://github.com/gedegithub/maven-pipeline.git'
        git branch: "${params.BRANCH_NAME}", url: 'https://github.com/votre-utilisateur/votre-repo.git'
10       }
11     }
12     stage('Build'){
13       steps {
14         sh 'mvn clean compile'
15       }
16     }
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
        publishHTML([reportDir: 'target/site', reportFiles: 'checkstyle.html', reportName: 'Checkstyle'])
        }
    }
22   }
23 }
