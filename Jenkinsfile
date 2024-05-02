pipeline {
    agent any
    environment {
        DOCKER_COMPOSE_VERSION = '1.25.0' // 사용할 Docker Compose의 버전
        GITLAB_TOKEN = credentials('wns1915') // Jenkins에 저장된 GitLab Token의 ID
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'release', credentialsId: 'wns1915', url: 'https://lab.ssafy.com/wns1915/oringe.git' // GitLab 리포지토리
            }
        }
		stage('Update Local Repository') {
            steps {
                script {
						withCredentials([usernamePassword(credentialsId: 'wns1915', usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')]) {
							sh '''
							cd /home/ubuntu/oringe
							echo url=https://$GIT_USERNAME:$GIT_PASSWORD@lab.ssafy.com/wns1915/oringe.git' > .git/credentials
							git config credential.helper 'store --file=.git/credentials'
							git pull origin release
							'''
						}
                }
            }
        }
        stage('Build Docker Images') {
            steps {
                script {
                    sh 'docker-compose -f /home/ubuntu/oringe/devway/docker-compose.yml build --no-cache app'
                    sh 'docker-compose -f /home/ubuntu/oringe/devway/docker-compose.yml up -d app'
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
					sh 'docker-compose -f /home/ubuntu/oringe/devway/docker-compose.yml build --no-cache nginx '
                    sh 'docker-compose -f /home/ubuntu/oringe/devway/docker-compose.yml up -d nginx '
                    sh 'docker-compose -f /home/ubuntu/oringe/devway/docker-compose.yml up -d certbot'
                }
            }
        }
    }
}
