pipeline {
    agent any
    environment {
		dockerhub = credentials('dockerhub')
        gitRepoUrl = 'https://github.com/RoySCV/Jenkins.git'
        gitRev = 'main'
        credsId = 'github-user-and-access-token'
	}
    stages {
       stage('Clone repository') {
            steps {
                echo "Cloning \n  Repo: ${gitRepoUrl}\n  Revision: ${gitRev}\n  CredsId: ${credsId}\nto dir: \n  ${WORKSPACE}"
                git(url: gitRepoUrl, branch: gitRev, credentialsId: credsId)
            }
        }

        stage('Docker-Compose UP') {
            steps {
                    echo '----------- Login Docker Hub -----------'
                    sh "echo ${dockerhub_PSW} | docker login -u ${dockerhub_USR} --password-stdin"
                    echo '----------- Docker-Compose Up -----------'
                    sh "docker-compose -f docker-compose-python-web-app.yaml up -d"
            }
        }

        stage('HealthCheck') {
            steps {
                    echo '----------- Checking Python-Web-App Responds OK -----------'
                    timeout(time: 1, unit: 'MINUTES') {
                        sh """
                            echo "Checking connection at:  http://localhost:80"
                            echo "Executing: docker exec nginx-reverse-proxy bash -c 'curl -s -f http://localhost:80'"
                            until docker exec nginx-reverse-proxy bash -c "curl -s -f http://localhost:80"
                            do
                                echo "Sleeping for 5 seconds before re-checking.."
                                sleep 5
                            done
                            echo OK - Connection is OK
                            exit 0
                        """
                    }
            }
        }

    }
}

