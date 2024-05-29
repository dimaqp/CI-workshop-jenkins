pipeline {
    agent any
    parameters {
        string(
            name: 'GREET',
            defaultValue: 'Hello',
            description: 'Greeting'
        )
    }
    stages {
        stage('Greet') {
            steps {
                script {
                    echo "${params.GREET}, Milo"
                }
            }
        }
    }

}