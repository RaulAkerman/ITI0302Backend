# ITI0302


# Instructions:

For documentation got to ---> https://gitlab.cs.ttu.ee/kkinks/iti0302/-/wikis/Documentation

To run this project locally start docker-compose.yml file (you must have Docker installed on your pc). After you start the docker launch WebprojectApplication.

The URL for the TalTech server is http://team6.hopto.org (http://193.40.255.16/api/ for direct backend access (cannot be accessed) and http://193.40.255.16 for frontend access without custom url)

Running the backend on your pc:
Make sure you have docker and postgre installed.

1. Clone repo from gitlab (https://gitlab.cs.ttu.ee/kkinks/iti0302)
2. Make sure you have postgre and docker installed
3. Changing the application.properties might be neccessary(local postgres password, localhost address)
4. Run WebprojectApplication.java class

Running the frontend on your PC:
1. Clone repo from gitlab (https://github.com/RaulAkerman/ITI0302FrontEnd)
2. Using the IDE terminal input the commands:
	a. npm install
	b. npm run serve

# Live access:

To access the server you need to be logged in using a public/private key pair (Using PuTTy for instance)
Running the backend on the server(must be in the same location as backend .gitlab-ci.yml file):
1. sudo docker-compose up <- starts backend
2. sudo docker-compose down <- closes backend

How to run frontend on the Server (must be in the same location as frontend .gitlab-ci.yml file):
1. sudo docker-compose up <- starts frontend
2. sudo docker-compose down <- closes frontend
