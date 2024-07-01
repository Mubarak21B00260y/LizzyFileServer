# Overview

Liizy File server  is a full-stack  documents management application that enables  educators, businesses   have a platform where their users or  audience can acess  business and academic document files from. It offers features such as user registration, secure email validation, document management (including creation, updating, sharing, and deletion).   The application ensures security using JWT tokens and adheres to best practices in REST API design. The backend is built with Spring Boot 3.2.6 and Spring Security 6.2.4 and Java 17.  and   the frontend  was  built  with React -Vite , Javscript , coupled with other UI libaries in the React ecosystem for styling .

# Features

- **User Registration**: Users can register for a new account.
- **Email Verification**: Account registrations  are activated using secure email validation codes.
- **User Authentication**: Existing users can log in to their accounts securely.
- **ForgotPassword**: Users can recover thier accounts via email verification codes.
- **Accessing document files**: Users can acess, view, share, and download all forms of  document files uploaded on the platform.
- **Admin priviliges**: Admin  uploads files with  title and description and upload date , being timestamps generated by the server.
- **Platform Admin**: Has  edit, upload, delete, download, share, search privileges, additionally, the Admin can  also see number of downloads  and shares for each file.
  

## ER diagram

![FileServerERdiagram drawio(1)](https://github.com/Mubarak21B00260y/LizzyFileServer/assets/93958028/371a8199-a10e-40f3-8ea8-19c1fce073ba)



# Technologies Used

## Backend (Lizzy File Server)

- Spring Boot 3.2.6
- Spring Security 6.2.4
- JWT Token Authentication
- Spring Data JPA
- My SQL 8.3 Database
- Lombok annotations
- Thymeleaf for email templates
- Maven build tool
- Devtools
  

## Frontend (Lizzy File Server)

- React- vite 
- Component-Based Architecture
- Tailwind CSS
- Material UI
- Shadcn Ui
- Headless UI
- Jwt-decode
- npm build tool


# License

This project is licensed under the MIT license . See the LICENSE file for details.

# Getting Started

## Perequisites
- Install java 17 from openJDK
- Install Node.Js from the  Node.js official website
- Install and configure MYSQL server 
- Clone the repo `git clone https://github.com/Mubarak21B00260y/LizzyFileServer`

## Backend
- Go to your your project folder from your terminal
- cd Lizzy-fileServer
- Run: `mvn clean install` to install  all dependencies( be sure to have maven installed )
- Run: `mvn spring-boot:run`
- NB: Make sure to modify the spring data source url,name, and password to match that of your local machines database and schema configuration

## Frontend
- In that same Lizzy-fileServer directory 
- cd into Client, then cd frontend
- Run:`npm install` to install all dependencies
- Run : `npm run dev`
- It will open your browser at  `http://localhost:5175`

# Contributors

- [Mohammed Mubarak](https://github.com/Mubarak21B00260y)

# Acknowledgments

Special thanks to the developers and maintainers of the technologies used in this project. Their hard work and dedication have made this project possible.
Another appreciation to Ali Bouali, from whom this project drew inspiration.
