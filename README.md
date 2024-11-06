# Player Management System

A REST API service for managing players data, developed in Java with Spring Boot.
This project provides two endpoints to retrieve players, with support for pagination and error handling.
It uses JPA for data persistence and includes a CSV loader for bulk data import.

## Features

- Two GET endpoints:
    * Retrieve all players (with pagination)
    * Get player details by ID
- Load players from a CSV file if the database is empty
- Basic error handling
- Basic tests

## Prerequisites

To run this project, ensure you have the following installed:

- **Java 17** or above
- **Maven** 3.9.6+ for building the project
## Future Enhancements (If I Had Infinite Time...)

- **Handle Reserved Keywords in CSV:**
  I encountered an issue with the field name `throws` as it is a reserved word in Java. I replaced it with `throwingHand` in the CSV file. If I had more time, I would handle the original name and find a more elegant solution to map the field correctly without altering the CSV.

- **Pagination Flexibility:**
  Currently, pagination is enforced, with default values for `page` and `size` if not provided by the client. If I had more time, I would allow clients to choose whether they want pagination or to retrieve all players at once (though this could cause performance issues with large datasets).

- **Encrypt Sensitive Data:**
  I would implement encryption for sensitive data in the `application.yaml` file to ensure that any sensitive configuration data (e.g., database credentials) is stored securely.

- **MapStruct for DTO Conversion:**
  I would use [MapStruct](https://mapstruct.org/) to convert between `Player` and `PlayerDto`. This would improve code maintainability and performance, especially when the data mapping becomes more complex.

- **Upgrade Dependencies:**
  I would address any dependency vulnerabilities highlighted by IntelliJ to ensure the application remains secure and up to date.
  
- **Use Enums for Constant Fields:**
  For fields with fixed values (e.g., `bats`, `throws`), I would refactor the implementation to use `enum` types instead of `String`. This would provide better type safety and ensure that only valid values are used.

- **Improve Error Messaging:**
  I would provide more detailed and user-friendly error messages. For example, instead of a generic error response when invalid input is provided, I would include information on which input is incorrect and how the client can correct the issue.

- **Use `LocalDate` for Date Fields:**
  I would replace `String` with `LocalDate` for date fields like `birthDate`, `deathDate`, etc. This would make the code more semantically accurate and easier to work with.

- **Investigate `null` vs `empty` Values in DB:**
  I would investigate why some fields in the database are `null` while others are empty. It would be important to ensure consistent handling of optional fields, whether they are `null` or empty strings.

- **Add More Tests:**
  I would write additional tests to cover edge cases and ensure the robustness of the application. This includes testing for invalid inputs, empty datasets, and performance under large volumes of data.