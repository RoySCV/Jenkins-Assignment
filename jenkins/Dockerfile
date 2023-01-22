FROM jenkins/jenkins:2.387

# run as root
USER 0

# update packages
RUN apt-get update -y 

# install dependencies
RUN apt-get install -y vim ca-certificates gnupg lsb-release
RUN apt-get install \
     ca-certificates -y \
     curl -y \
     gnupg -y \
     lsb-release -y

# add docker repo     
RUN mkdir -p /etc/apt/keyrings
RUN curl -fsSL https://download.docker.com/linux/debian/gpg | gpg --dearmor -o /etc/apt/keyrings/docker.gpg
RUN echo "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/debian \
   $(lsb_release -cs) stable" | tee /etc/apt/sources.list.d/docker.list > /dev/null

# update added repo packages   
RUN apt-get update -y

# install docker tools   
RUN apt-get install -y docker-ce docker-ce-cli containerd.io docker-compose docker-compose-plugin

# give permissions on docker
RUN usermod -aG docker jenkins