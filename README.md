<h1 align="center"> University_info </h1>

University-info is simple Spring Boot java project with the console interface for university, which consists of departments and lectors.


## Supported commands:
- start - greeting command, contains a list of available commands (which can also be obtained using the start command)
- Who is head of department {department_name};
  (example "Who is head of department Chemistry")
- Show statistics for {department_name} ;
- Show the average salary for the department {department_name};
- Show count of employee for {department_name};
- Global search by {template};

## Project structure:

- DAO (Data Access Object) - DAO is the data access layer responsible for interacting with the database. This layer
  implements operations related to retrieving, storing, updating, and deleting data.
- Service - The service layer is responsible for the business logic of the application. It handles data processing,
  executes business rules, and coordinates actions between the DAO and other components of the application.
- Console -
  this level is responsible for processing information through the console.

## Instructions for launching the project:
1. Fork this project;
2. Clone the project from GitHub;
3. Fill Application properties with the necessary data.
4. Run the project.