/* groovylint-disable BlockStartsWithBlankLine, GStringExpressionWithinString */
pipeline {
    agent any

    tools {
        maven 'maven396'
        jdk 'jdk8'
    }

    //task2
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

        stage('build docker image') {
            steps {
                sh "docker build -t dimaqp/petclinic:1.0.$env.BUILD_NUMBER ."
            }
        }
        //push docker image
        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'docker_hub_credentials',
                    usernameVariable: 'username',
                    passwordVariable: 'password'
                )]) {
                    // login to Docker
                    sh "docker login -u $username -p $password"
                    // push Docker image
                    sh "docker push dimaqp/petclinic:1.0.$env.BUILD_NUMBER"
                }
            }
        }
    }
}
