echo deploy jenkins: 

echo Running "docker build -f ../jenkins/Dockerfile -t jenkins-master ."
docker build -f ../jenkins/Dockerfile -t jenkins-master .

timeout 5 

echo Running "docker-compose -f ../docker-compose-jenkins.yaml up -d"
docker-compose -f ../docker-compose-jenkins.yaml up -d

exit 0