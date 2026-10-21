# Testing - CMPT 276 Assignment 2

## Testing Approach

This project uses automated tests with JUnit and Spring Boot's testing framework.

The tests are organized into separate classes to check application startup, input validation, controller behavior, and database operations.

## Test Files

The project contains the following test classes:

- `Asn2ApplicationTests.java`
- `StaffRatingValidationTests.java`
- `StaffRatingControllerTests.java`
- `StaffRatingRepositoryTests.java`

## How to Run Tests

### Windows

Open a terminal in the project directory and run:

```powershell
.\mvnw.cmd test
```

### macOS / Linux

```bash
./mvnw test
```

Maven will compile the test code and run the automated tests.

## Test Results

The most recent local test run completed successfully:

- Tests run: 16
- Failures: 0
- Errors: 0
- Skipped: 0

Result: BUILD SUCCESS

## Testing Environment

- Java 17
- Spring Boot
- Maven
- JUnit
- Spring Boot Test
- H2 in-memory database for local automated testing

## Manual Testing

The deployed application was also checked through its web interface.

The following basic workflows were manually tested:

- Opening the home page
- Creating a staff rating
- Viewing a rating
- Editing a rating
- Deleting a rating

## Notes

The application uses H2 for local development and automated testing, while the deployed application uses PostgreSQL on Render.