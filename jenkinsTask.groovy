pipeline {
    agent any

    tools {
        maven 'maven_home'
    }

    stages {  // Correct placement and naming of the stages block
        stage('compile') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('install') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}
