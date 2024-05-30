pipeline {
    agent any

    tools {
        maven 'maven396'
        jdk 'jdk8'
    }

    stages {
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
