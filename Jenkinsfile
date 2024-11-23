pipeline {
    agent any

    stages {
        stage('GetProject') {
            steps {
                git branch: 'master', url: 'https://github.com/LisaR90/lisaspetitions.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean:clean'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }
        stage('Archive') {
            steps {
                archiveArtifacts allowEmptyArchive: true, artifacts: '**/lisaspetitions*.war'
            }
        }
        stage('Approve Deployment') {
            steps {
                input "Deploy to Production?"
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker build -f Dockerfile -t myapp .'
                sh 'docker rm -f "myappcontainer" || true'
                sh 'docker run --name "myappcontainer" -p 9090:8080 --detach myapp:latest'
            }
        }
    }

    post {
        always {
            // Notify that the pipeline has finished
            echo 'Pipeline finished!'
        }
    }
}
