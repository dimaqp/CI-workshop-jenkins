/* groovylint-disable BlockStartsWithBlankLine */
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

        stage {
            steps {
                sh "docker build -t dimaqp/petclinic:$env.BUILD_NUMBER ."
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
                    //login to docker
                    sh "docker login -u $username -p $password"
                    sh "docker push dimaqp/petclinic:$env.BUILD_NUMBER"
                        }
            }
        }
    }
}
