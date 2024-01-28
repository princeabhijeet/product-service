pipeline {
    
    agent any

    tools {
        maven 'maven'
    }
    
    options {
        skipDefaultCheckout(true)
    }

    stages {
        stage('SCM Checkout') {
            steps {
                echo '[SCM Checkout]: START'
                checkout([$class: 'GitSCM',
                    branches: [[name: 'main']],
                    userRemoteConfigs: [[url: 'https://github.com/princeabhijeet/product-service.git']],
                    credentialsId: 'git_credentials'])
                echo '[SCM Checkout]: COMPLETE'
            }
        }

        stage('Maven: Build JAR') {
            steps {
                echo '[Maven: Build JAR]: START'
                sh "mvn clean install -DskipTests=true"
                echo '[Maven: Build JAR]: COMPLETE'
            }
        }

        stage('Docker: Build Image') {
            steps {
                echo '[Docker: Build Image]: START'
                sh "docker rmi princeabhijeet/product-service:latest || true"
                sh "docker build -t princeabhijeet/product-service:latest ."
                echo '[Docker: Build Image]: COMPLETE'
            }
        }

        stage('Docker: Push Image') {
            steps {
                echo '[Docker: Push Image]: START'
                withDockerRegistry([credentialsId: 'dockerhub_credentials', url: 'https://index.docker.io/v1/']) {
                    sh "docker push princeabhijeet/product-service:latest"
                }
                echo '[Docker: Push Image]: COMPLETE'
            }
        }
        
    }
}