# SolFlow Application

SolFlow is a Java Swing application that provides a simple user interface for user authentication, including login and signup functionalities.

## Project Structure

```
solflow
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── solflow
│   │   │           ├── Main.java
│   │   │           ├── ui
│   │   │           │   ├── HomeScreen.java
│   │   │           │   ├── LoginScreen.java
│   │   │           │   └── SignupScreen.java
│   │   │           └── util
│   │   │               └── UIUtils.java
│   │   └── resources
│   │       └── application.properties
├── build.gradle
├── settings.gradle
├── README.md
└── .gitignore
```

## Features

- Home Screen: Displays a welcome message and buttons for login and signup.
- Login Screen: Allows users to enter their credentials to log in.
- Signup Screen: Enables new users to create an account.

## Setup Instructions

1. Clone the repository:
   ```
   git clone <repository-url>
   ```

2. Navigate to the project directory:
   ```
   cd solflow
   ```

3. Build the project using Gradle:
   ```
   ./gradlew build
   ```

4. Run the application:
   ```
   ./gradlew run
   ```

## Usage

Upon launching the application, users will be greeted with a welcome message and options to log in or sign up. Follow the prompts to navigate through the application.

## License

This project is licensed under the MIT License. See the LICENSE file for details.