# File Service RESTful API
This is a sample Spring boot project which provides RESTful APIs to support file upload related functionalities.

## Run the application in local
To run the application from local using Maven, 

cd fileservice
mvn spring-boot:run

## Swagger UI
To look at the available rest endpoints, input parameters and to take it for a test drive, access the Swagger UI URL below:
http://localhost:8080/swagger-ui.html#/file45controller

## Overview
Below is a high level overview of supported functionalities -

#### File upload
Lets you upload a file along with a few meta data fields like author, version.
The API stores the meta data in an in-memory database (H2) and the file content in local file system.
In addition to the meta data which are provided explicitly, the API also fetches and stores basic file meta data like file name, size etc., There is a maximum file size limit of 2MB (configurable from application.properties)


#### Fetch all meta data
Fetches all the meta data stored in the in memory database.

#### Search files
Retrieves the file meta data based on search criteria specified in the input like File name, File id etc.,

#### File download
Downloads the file content based on file id

#### Scheduler and email functionalities
The scheduler polls for all the files uploaded in a specific time (currently set as 1 minute for easy testing - configurable).
It sends out an email report via SMTP (SMTP server, Email and Recipient email are configurable from application.properties).


