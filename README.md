﻿# practice-API

This project is a Spring Boot RESTful API for managing products. It supports operations for creating, updating, viewing, and listing products with basic authentication and authorization.

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/qwufy/practice-api
    cd product-management-api
    ```

2. Build the project:
    ```bash
    mvn clean install
    ```

3. Run the application:
    ```bash
    mvn spring-boot:run
    ```

The application will start on `http://localhost:8080/api/products`.

### Testing
Tested with "Postman Agent"

1. to create an item:
   Prerequisites: "POST, URL
                   nameKazakh,
                   nameRussian,
                   quantity,
                   price"

2. to update an item:
   Prerequisites: "PUT, URL/{id}
                   nameKazakh,
                   nameRussian
                   quantity,
                   price"

3. to get an item:
   Prerequisites: "GET, URL/{id}"
### Example:
![image](https://github.com/qwufy/practice-api/assets/129245004/5cbf3a9e-deb7-4e75-8653-638953148ca0)

![image](https://github.com/qwufy/practice-api/assets/129245004/08bf0739-9f32-48df-ae4b-7b1854c3cde4)

### Exporting

1. Export to Excel
    - **URL:** `/api/reports/excel`
    - **Method:** `GET`
    - **Roles Allowed:** `ROLE_EDITOR`, `ROLE_VIEWER`
    - **Description:** Exports product data to an Excel file and prompts the user to download the file.
   **Example Request:**
   `http://localhost:8080/api/reports/excel` - and you will get an excel file with the data

2. Export to PDF
    - **URL:** `/api/reports/pdf`
    - **Method:** `GET`
    - **Roles Allowed:** `ROLE_EDITOR`, `ROLE_VIEWER`
    - **Description:** Exports product data to an PDF file and prompts the user to download the file.
   **Example Request:**
   `http://localhost:8080/api/reports/pdf` - and you will get an PDF file with the data


