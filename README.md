# CMPT 276 Assignment 2 - Rate CS Teaching Staff

## Project Overview

This is a Spring Boot web application for rating CS teaching staff. Users can create, view, edit, and delete staff ratings.

Each rating includes the staff member's name, email, role, three scores (clarity, niceness, and knowledge), and an optional comment.

## Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- Thymeleaf
- PostgreSQL (Render deployment)
- H2 Database (local development and testing)
- Maven
- Docker
- Render

## Features

- View all staff ratings on the home page.
- View detailed information for each rating.
- Create new staff ratings.
- Edit existing ratings.
- Delete ratings.
- Validate user input and display error messages.
- Calculate an overall score from the three rating categories.
- Demonstrate object-oriented design using abstract classes and polymorphism.

## How to Run Locally

### Requirements

- Java 17
- Internet connection for downloading Maven dependencies

### Steps

1. Clone the GitHub repository:

   ```bash
   git clone https://github.com/Emily-26-split/cmpt276-asn2.git
   ```

2. Open the project directory:

   ```bash
   cd cmpt276-asn2
   ```

3. Run the Spring Boot application.

   On Windows:

   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

   On macOS or Linux:

   ```bash
   ./mvnw spring-boot:run
   ```

4. Open the application in a browser:

   http://localhost:8080

The application uses an in-memory H2 database by default when running locally. Local data will not be preserved after the application stops.

## How to Deploy on Render

The application is deployed on Render using Docker and PostgreSQL.

1. Push the project to a GitHub repository.
2. Create a PostgreSQL database on Render.
3. Create a new Render Web Service using the GitHub repository.
4. Select Docker as the runtime environment.
5. Set the Dockerfile path to `./Dockerfile`.
6. Select the same region as the PostgreSQL database.
7. Configure the following environment variables:

   - `DB_URL`: PostgreSQL JDBC connection URL
   - `DB_USERNAME`: PostgreSQL database username
   - `DB_PASSWORD`: PostgreSQL database password

8. Deploy the web service.
9. Open the Render URL to access the application.

Database credentials are stored in Render environment variables and are not committed to GitHub.

## Live Application

https://cmpt276-asn2-u9zg.onrender.com

## GitHub Repository

https://github.com/Emily-26-split/cmpt276-asn2

## Testing

The project includes automated tests for validation, controller behavior, and database operations.

To run the tests on Windows:

```powershell
.\mvnw.cmd test
```

On macOS or Linux:

```bash
./mvnw test
```

See `TESTING.md` for more information about the tests.

## Email Uniqueness Rule

Email addresses are not required to be unique in this application.
Multiple ratings may refer to the same teaching staff member, so
the same email address can appear in more than one rating record.
Each rating is uniquely identified by its automatically generated ID.