GITHUB USERS REPOS API

Description

This API lists GitHub repositories for users which are not forks. It also provides information about branches and the latest commit for each repository.

Requirements

Java 21
Spring Boot 3.2.2

Installation and Running

1 clone the repository
2 Navigate to the project folder: cd your-project-folder
3 Run the application: mvn spring-boot:run

Usage

Request:

GET /{username}

Headers:

Accept: application/json

Response:

200 OK: Returns a list of the user's repositories (which are not forks) along with branch and commit information.

404 Not Found: If the user does not exist.

Sample response

[
{
"name": "Bluetooth_LowEnergy_App",
"owner": "WojtekSzewczy",
"branches": [
{
"name": "BT-01-05",
"commit": {
"sha": "75a664a6776ec9253b68581e65f815c70f972935"
}
},
{
"name": "master",
"commit": {
"sha": "98906d8d567b706985d70f70acf8523742b4e443"
}
}
]
}
]

Error Handling

Sample Response for Non-Existing User:

{
"message": "user hyugrdesdrftghjklkmnbvcxsdftgjkmn not found",
"status": 404
}