# 🎬 BookMyScreen (BMS) - Backend
BookMyScreen is a full-stack movie ticket booking application developed as part of my LaunchCode software development project. 
This repository contains the backend of the application, built using Java, Spring Boot, Spring Data JPA, Hibernate, and MySQL. 
The backend provides REST APIs that connect the React frontend with 
the MySQL database and manages movies, theaters, shows, seats, users, authentication, and bookings. 
Users can browse movie information, view available shows, authenticate using email OTP, select available seats, create bookings, and view their booking history. 
This project demonstrates REST API development, CRUD operations, relational database relationships, authentication, DTOs, service-layer architecture, exception handling, and full-stack frontend/backend integration.
---
## 🚀 Features
- 🎥 Create, retrieve, update, and delete movie information
- 🔍 Search and filter movies
- 🏢 Manage theater information
- 🎞️ Manage movie shows
- 📅 Retrieve shows by movie, location, and date
- 💺 Manage seats for individual shows
- ✅ Track available and booked seats
- ⚠️ Prevent already-booked seats from being booked again
- 🎟️ Create movie ticket bookings
- 📋 Retrieve booking history for the logged-in user
- 👤 Create and manage user accounts
- 📧 Authenticate users using email OTP
- 🔐 JWT access-token authentication
- 🔄 Refresh-token support
- 🔑 Bearer-token authorization for protected requests
- 💳 Store booking and payment information
- 🗄️ Store application data in MySQL
- 🔗 Connect the Spring Boot backend with the React frontend
- 🌱 Seed initial movie, theater, and show data
- ⚠️ Centralized exception handling
- 🌐 CORS configuration for frontend/backend communication
---
## 🛠️ Technologies Used
### Backend
- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Maven
- REST APIs
### Database
- MySQL
- MySQL Workbench
### Authentication
- Email OTP Authentication
- JSON Web Tokens (JWT)
- Access Tokens
- Refresh Tokens
- Bearer Token Authorization
### Frontend Integration
- React
- JavaScript
- Axios
- Vite
- React Router
- CSS
### Development & Testing Tools
- IntelliJ IDEA
- Visual Studio Code
- Postman
- Git
- GitHub
---
## 📁 Project Structure
The backend follows a layered Spring Boot architecture.
```text
bookmyscreen-backend/
│
├── src/
│   ├── main/
│   │   ├── java/com/example/bookmyscreenbackend/
│   │   │
│   │   ├── config/
│   │   ├── controller/
│   │   ├── dto/
│   │   ├── exception/
│   │   ├── model/
│   │   ├── repository/
│   │   ├── service/
│   │   │
│   │   └── BookmyscreenBackendApplication.java
│   │
│   └── resources/
│       └── application.properties
│
├── pom.xml
└── README.md
```
### Main Backend Layers
- **Config** – Contains CORS configuration and development data seeders.
- **Controller** – Handles HTTP requests and exposes REST API endpoints.
- **DTO** – Transfers request data between the frontend and backend.
- **Exception** – Provides centralized exception handling.
- **Model** – Contains the main JPA entities and enums.
- **Repository** – Communicates with MySQL using Spring Data JPA.
- **Service** – Contains application business logic.
---
## 🗃️ Main Model Classes
The main model classes represent the important data used by the BookMyScreen application.
### 🎬 Movie
`Movie` stores the main information about each movie, including:
- Title
- Description
- Duration
- Genre
- Language
- Release date
- Poster URL
- Banner URL
- Trailer URL
- Certificate
- Rating
- Active status
- Created and updated timestamps
### 🏢 Theater
`Theater` represents a movie theater where shows are available.
### 🎞️ Show
`Show` represents a particular screening of a movie and connects movie and theater information with show-specific details.
### 💺 ShowSeat
`ShowSeat` represents an individual seat for a particular movie show.
Seat availability is represented using `SeatStatus`:
```text
AVAILABLE
BOOKED
BLOCKED
```
### 👤 User
`User` stores user account information used by the application.
### 🎟️ Booking
`Booking` stores completed movie booking information, including the user, selected show, seats, booking amount, and payment-related information.
### 🔑 RefreshToken
`RefreshToken` supports authentication and token management.
---
## 🔗 Application Architecture
BookMyScreen uses the following full-stack architecture:
```text
React Frontend
      ↓
Axios
      ↓
Spring Boot REST Controllers
      ↓
Service Layer
      ↓
Repository Layer
      ↓
Spring Data JPA / Hibernate
      ↓
MySQL Database
```
The React frontend sends requests to the Spring Boot REST API using Axios.
Controllers receive the requests and pass the required information to the service layer.
The service layer handles the application's business logic.
Repositories communicate with the MySQL database using Spring Data JPA and Hibernate.
The backend returns JSON responses to the React frontend.
---
## ⚙️ Installation and Setup
### Prerequisites
Before running BookMyScreen, install:
- Java 21
- IntelliJ IDEA
- MySQL
- MySQL Workbench
- Git
- Maven or the included Maven Wrapper
- Postman
- Node.js and npm for the frontend
### 1. Clone the Backend Repository
```bash
git clone https://github.com/sivabasani95/bookmyscreen-backend-SivaBasani.git
```
Move into the project:
```bash
cd bookmyscreen-backend-SivaBasani
```
### 2. Open the Backend
Open the project in IntelliJ IDEA.
Maven will load the dependencies defined in:
```text
pom.xml
```
### 3. Configure MySQL
Configure the MySQL connection in:
```text
src/main/resources/application.properties
```
Example:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/YOUR_DATABASE_NAME
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.jpa.hibernate.ddl-auto=update
```
Replace the example values with your local MySQL configuration.
> ⚠️ Do not commit database passwords, email credentials, JWT secrets, or other private credentials to GitHub.
### 4. Configure Authentication
BookMyScreen uses email OTP authentication and JWT tokens.
Configure the required email and JWT settings locally.
Sensitive credentials should not be committed directly to the GitHub repository.
### 5. Run the Spring Boot Backend
Run:
```text
BookmyscreenBackendApplication.java
```
The backend runs locally at:
```text
http://localhost:8080
```
The application APIs use the `/api` path.
Example:
```text
http://localhost:8080/api/movies
```
### 6. Run the React Frontend
Clone the frontend repository:
```bash
git clone https://github.com/sivabasani95/Movie-Ticket-Booking-App-Siva-Basani.git
```
Install the frontend dependencies:
```bash
npm install
```
Start the frontend:
```bash
npm run dev
```
The React frontend communicates with the Spring Boot backend through REST APIs.
---
## 🔐 Authentication
BookMyScreen uses email OTP authentication with JWT access tokens.
### Authentication Flow
```text
User enters email
        ↓
Backend sends OTP
        ↓
User enters OTP
        ↓
Backend verifies OTP
        ↓
Access token is generated
        ↓
Frontend stores the access token
        ↓
Token is sent with protected requests
```
Protected requests send the token in the Authorization header:
```text
Authorization: Bearer <access-token>
```
For example, authenticated users can retrieve their booking history through:
```http
GET /api/bookings/me
```
---
## 🎟️ Booking Flow
The booking workflow connects the React frontend, Spring Boot backend, and MySQL database.
```text
Browse Movies
      ↓
Select Movie
      ↓
Select Show
      ↓
View Seat Layout
      ↓
Select Available Seats
      ↓
Checkout
      ↓
Proceed To Pay
      ↓
Create Booking
      ↓
Save Booking in MySQL
      ↓
Mark Seats as Booked
      ↓
Profile → Bookings
```
During booking:
1. The user selects a movie and show.
2. The application displays the seat layout.
3. The user selects available seats.
4. The checkout page displays the booking information.
5. The user proceeds with the booking.
6. The frontend sends the booking request to the backend.
7. The backend identifies the logged-in user using the access token.
8. The backend verifies the selected seats.
9. The booking is stored in MySQL.
10. The selected seats are marked as booked.
11. The completed booking appears in the user's Bookings page.
This prevents already-booked seats from being booked again for the same show.
---
## 🔌 Main Backend APIs
BookMyScreen provides REST APIs for the main application areas:
- Movies
- Theaters
- Shows
- Show Seats
- Users
- Authentication
- Bookings
Examples of endpoints used in the application include:
```http
GET /api/movies
GET /api/movies/{id}
POST /api/auth/send-otp
POST /api/auth/verify-otp
GET /api/users/me
POST /api/bookings
GET /api/bookings/me
```
Protected endpoints require a valid Bearer access token.
---
## 🌱 Data Seeders
The backend contains development data seeders for:
- Movies
- Theaters
- Shows
These seeders help populate the application with initial data for development and testing.
---
## 🧪 Testing
The backend APIs were tested using Postman.
Testing included:
- Movie CRUD operations
- Movie retrieval and filtering
- Theater and show data
- Show-seat availability
- Sending email OTPs
- Verifying OTPs
- Receiving access tokens
- Bearer-token authorization
- Retrieving logged-in user information
- Creating bookings
- Retrieving booking history
- Preventing already-booked seats from being booked again
---
## 🖼️ Wireframes
Wireframes were created during the planning and design stage of BookMyScreen. They helped plan the application's screens and user flow before and during development.
### 🔗 Wireframes
[View BookMyScreen Wireframes](https://docs.google.com/document/d/19Sx-incJuMByVAypdokbv7JhdpdvWfFC/edit)
---
## 🗄️ ER Diagram
The Entity Relationship Diagram (ERD) represents the relational database structure used by BookMyScreen and shows the relationships between the main application entities.
### 🔗 ER Diagram

[View BookMyScreen ER Diagram](https://docs.google.com/document/d/1FIHqSKgIXoLx7xlfTcds52rQavudOlQKC4jVEJYuo88/edit?tab=t.0)
---
## 📋 Project Management
The backend project was planned and tracked using Trello.
### 🔗 Trello Board
[View BookMyScreen Backend Trello Board](https://trello.com/b/kc7JnHCJ/bookmyscreen-back-end)
---
## 🌐 Live Demo
The React frontend is deployed on Netlify.
### 🔗 Live Application
[Open BookMyScreen](https://sivabasani-movie-ticket-booking.netlify.app/)
The Spring Boot backend currently runs locally during development:
```text
http://localhost:8080
```
Deploying the backend and database to a cloud environment is included as a future improvement.
---
## 📁 GitHub Repositories
### 🎨 Frontend Repository
[BookMyScreen Frontend GitHub Repository](https://github.com/sivabasani95/Movie-Ticket-Booking-App-Siva-Basani.git)
### ⚙️ Backend Repository
[BookMyScreen Backend GitHub Repository](https://github.com/sivabasani95/bookmyscreen-backend-SivaBasani.git)
---
## 🔗 All Project Links
| Resource | Link |
|---|---|
| 🎨 Frontend Repository | [View Frontend GitHub](https://github.com/sivabasani95/Movie-Ticket-Booking-App-Siva-Basani.git) |
| ⚙️ Backend Repository | [View Backend GitHub](https://github.com/sivabasani95/bookmyscreen-backend-SivaBasani.git) |
| 🌐 Live Application | [Open BookMyScreen](https://sivabasani-movie-ticket-booking.netlify.app/) |
| 🖼️ Wireframes | [View Wireframes](https://docs.google.com/document/d/19Sx-incJuMByVAypdokbv7JhdpdvWfFC/edit) |
| 🗄️ ER Diagram | [View ER Diagram](https://docs.google.com/document/d/1FIHqSKgIXoLx7xlfTcds52rQavudOlQKC4jVEJYuo88/edit?tab=t.0) |
| 📋 Trello Board | [View Backend Trello Board](https://trello.com/b/kc7JnHCJ/bookmyscreen-back-end) |
---
## 📌 Project Purpose
BookMyScreen was developed as part of my LaunchCode full-stack software development learning journey. The purpose of the project was to extend my original React movie ticket booking application into a full-stack application using Java, Spring Boot, and MySQL.
Through this project, I gained hands-on experience building REST APIs, implementing CRUD operations, creating JPA entities and database relationships, organizing backend code into controller, service, repository, DTO, and model layers, implementing OTP-based authentication, working with access and refresh tokens, managing movie shows and seat availability, preventing duplicate seat bookings, connecting a React frontend to a Spring Boot backend,
and storing application data in a relational database.
---
## ⚡ Unsolved Problems / Future Improvements
The current version provides the core functionality required for the
BookMyScreen movie ticket booking application. Future improvements include:
- 💳 Integrate a real payment gateway instead of the current test payment flow
- ☁️ Deploy the Spring Boot backend to a cloud hosting service
- 🗄️ Deploy MySQL to a production cloud database
- 🎫 Generate downloadable digital movie tickets
- 📱 Add QR codes to tickets
- 📧 Send booking confirmation emails
- ❌ Add complete booking cancellation functionality
- 💰 Add refund processing
- 🕒 Add temporary seat locking during checkout
- 📅 Separate upcoming and past bookings
- 👨‍💼 Add an admin dashboard for managing movies, theaters, shows, and bookings
- 📊 Add booking and sales analytics
- ⭐ Add movie reviews and user ratings
- 🔔 Add booking reminders and notifications
- 🔐 Continue improving authentication and authorization
- 🧪 Add more automated unit and integration tests
- ⚡ Improve API validation and error handling
- 📱 Continue improving accessibility and mobile responsiveness
---
## 👤 Developer
**Siva Basani**
BookMyScreen was created as part of my LaunchCode full-stack software development project.
---
⭐ Thank you for exploring BookMyScreen!