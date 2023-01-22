# Jenkins-Assignment

## Getting started

##### In Github:

1. Create a Github access token for Jenkins to clone this repository

##### In Jenkins:

1. Create Access Token, use the user name and password you get to login jenkins

##### In Docker Hub:

1. Create a Docker Hub credentials with id: `docker-hub-user-and-access-token` of type **username & password** and put your docker hub username & password

## Run

Execute the **seed-project-python-webapp** job


### Jenkins Contianer ###
## Add root permissions to jenkins user in the container:
1. Connect to the container as root: 
   docker exec -it -u root f55b381380a7 bash
2. run the following commands:
   - usermod -aG docker jenkins
   - chmod 666 /var/run/docker.sock