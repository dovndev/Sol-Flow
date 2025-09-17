# SolFlow Application

SolFlow is a modern Java Swing application with SQL database integration that provides secure user authentication, including login and signup functionalities with advanced features like theme switching, session management, and comprehensive validation.

## 🚀 Features

- **Modern UI**: Clean interface with FlatLaf dark/light theme support
- **Secure Authentication**: BCrypt password hashing with SQL database storage
- **User Management**: Complete registration and login system with validation
- **Session Management**: User session tracking with logout functionality
- **Database Integration**: SQLite database for persistent user storage
- **Theme Switching**: Toggle between light and dark themes
- **Comprehensive Logging**: SLF4J logging throughout the application
- **Input Validation**: Email format, password strength, and username validation
- **Testing**: Complete JUnit test suite for all components

## 📦 Dependencies

### Core Framework
- **Java 17+**: Modern Java LTS version with latest features
- **Gradle 8+**: Build automation and dependency management
- **Shadow Plugin 8.1.1**: Fat JAR creation for distribution

### UI Libraries
- **FlatLaf 3.2.5**: Modern look-and-feel for Swing applications
- **SwingX All 1.6.5-1**: Enhanced Swing components and utilities

### Database & Security
- **SQLite JDBC 3.43.0**: Lightweight embedded database
- **H2 Database 2.2.224**: Alternative in-memory/file database for testing
- **BCrypt 0.10.2**: Industry-standard password hashing library

### Utilities & Logging
- **Gson 2.10.1**: JSON serialization/deserialization for settings
- **SLF4J API 2.0.13**: Simple Logging Facade for Java
- **SLF4J Simple 2.0.13**: Simple logging implementation

### Testing
- **JUnit Jupiter 5.9.2**: Modern unit testing framework with assertions and parameterized tests

## 📁 Project Structure

```
Sol-Flow/
├── src/main/java/com/solflow/
│   ├── Main.java                    # Application entry point
│   ├── model/
│   │   └── User.java               # User entity model
│   ├── ui/
│   │   ├── AppFrame.java           # Main application window with menu
│   │   ├── HomeScreen.java         # Welcome screen with user status
│   │   ├── LoginScreen.java        # User login form
│   │   └── SignupScreen.java       # User registration form
│   ├── util/
│   │   ├── SqlUserStore.java       # SQL database user operations
│   │   ├── ThemeSettings.java      # Theme persistence and management
│   │   ├── Session.java            # User session management
│   │   ├── Validator.java          # Input validation utilities
│   │   └── DatabaseConfig.java     # Database configuration
│   └── data/
│       └── SignupData.java         # Data transfer object for signup
├── src/test/java/com/solflow/util/
│   ├── ValidatorTest.java          # Validation logic tests
│   └── SqlUserStoreTest.java       # Database operations tests
├── build.gradle                    # Build configuration and dependencies
├── settings.gradle                 # Gradle project settings
├── gradlew                         # Gradle wrapper script (Unix)
├── gradlew.bat                     # Gradle wrapper script (Windows)
├── .gitignore                      # Git ignore patterns
└── README.md                       # This documentation
```

## 🔧 Environment Setup Guide

### Java Installation and Configuration

#### 1. Install Java 17 (or higher)

**On Ubuntu/Debian:**
```bash
# Install OpenJDK 17
sudo apt update
sudo apt install openjdk-17-jdk

# Verify installation
java --version
javac --version
```

**On macOS:**
```bash
# Using Homebrew
brew install openjdk@17

# Add to PATH
echo 'export PATH="/opt/homebrew/opt/openjdk@17/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

**On Windows:**
1. Download OpenJDK 17 from [Adoptium](https://adoptium.net/)
2. Run the installer
3. Set JAVA_HOME environment variable

#### 2. Set JAVA_HOME Environment Variable

**On Linux/macOS:**
```bash
# Find Java installation path
sudo update-alternatives --config java
# or
whereis java

# Add to ~/.bashrc or ~/.zshrc
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

# Apply changes
source ~/.bashrc  # or ~/.zshrc
```

**On Windows:**
1. Open System Properties → Advanced → Environment Variables
2. Add new system variable: `JAVA_HOME` = `C:\Program Files\Eclipse Adoptium\jdk-17.0.x.x-hotspot`
3. Add `%JAVA_HOME%\bin` to PATH

#### 3. Verify Java Setup
```bash
echo $JAVA_HOME
java --version
javac --version
```

### Gradle Setup

#### Option 1: Use Gradle Wrapper (Recommended)
The project includes Gradle Wrapper, so no separate Gradle installation is needed:

```bash
# Make wrapper executable (Linux/macOS)
chmod +x gradlew

# Use wrapper for all commands
./gradlew --version
./gradlew build
./gradlew run
```

#### Option 2: Install Gradle Globally

**Using SDKMAN (Linux/macOS):**
```bash
# Install SDKMAN
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"

# Install Gradle
sdk install gradle 8.4
gradle --version
```

**Using Homebrew (macOS):**
```bash
brew install gradle
gradle --version
```

**Manual Installation:**
1. Download Gradle from [gradle.org](https://gradle.org/releases/)
2. Extract to `/opt/gradle` (Linux) or `C:\gradle` (Windows)
3. Add `GRADLE_HOME` and update PATH:

```bash
# Linux/macOS
export GRADLE_HOME=/opt/gradle/gradle-8.4
export PATH=$GRADLE_HOME/bin:$PATH

# Windows
# Add GRADLE_HOME=C:\gradle\gradle-8.4
# Add %GRADLE_HOME%\bin to PATH
```

### IDE Setup

#### IntelliJ IDEA
1. **Import Project**: File → Open → Select `build.gradle`
2. **Configure JDK**: File → Project Structure → Project → Project SDK → Java 17
3. **Gradle Settings**: File → Settings → Build → Gradle → Use Gradle from: 'gradle-wrapper.properties' file
4. **Enable Annotation Processing**: Settings → Build → Compiler → Annotation Processors → Enable

#### Visual Studio Code
1. **Install Extensions**:
   - Extension Pack for Java
   - Gradle for Java
   - Spring Boot Extension Pack (optional)

2. **Configure Java**:
   ```json
   // .vscode/settings.json
   {
     "java.home": "/usr/lib/jvm/java-17-openjdk-amd64",
     "java.configuration.runtimes": [
       {
         "name": "JavaSE-17",
         "path": "/usr/lib/jvm/java-17-openjdk-amd64"
       }
     ]
   }
   ```

#### Eclipse
1. **Import**: File → Import → Existing Gradle Project
2. **Configure JRE**: Window → Preferences → Java → Installed JREs → Add JRE → Standard VM → Java 17
3. **Set Project JRE**: Right-click project → Properties → Java Build Path → Libraries → Modulepath → JRE System Library → Edit → Java 17

### Database Setup

#### SQLite (Automatic)
- No manual setup required
- Database auto-created at `~/.solflow/solflow.db`
- Ensure write permissions to home directory
- Tables created automatically on first launch

#### H2 Database (Alternative)
```bash
# H2 Console (optional for debugging)
# Add to build.gradle dependencies:
# runtimeOnly 'com.h2database:h2'

# Access H2 Console at: http://localhost:8082
# JDBC URL: jdbc:h2:~/.solflow/solflow
```

## 🚀 Getting Started

### Quick Start
```bash
# 1. Clone the repository
git clone https://github.com/dovndev/Sol-Flow.git
cd Sol-Flow

# 2. Verify environment
java --version
./gradlew --version

# 3. Build and test
./gradlew clean build test

# 4. Run application
./gradlew run

# 5. Create distribution JAR
./gradlew shadowJar
```

### First Launch
1. **Home Screen**: Welcome message with Login/Signup options
2. **Create Account**: Click "Sign Up" to register a new user
3. **Login**: Use your credentials to access the application
4. **Theme Toggle**: Use View → Dark Theme to switch themes
5. **Database**: User data automatically saved to `~/.solflow/solflow.db`

## 🧪 Testing

### Run Tests
```bash
# Run all tests
./gradlew test

# Run specific test class
./gradlew test --tests "ValidatorTest"

# Run tests with detailed output
./gradlew test --info

# Generate test report
./gradlew test jacocoTestReport
# Report available at: build/reports/tests/test/index.html
```

### Database Testing
- **Temporary Databases**: Tests use temporary SQLite files
- **Isolated Tests**: Each test gets a fresh database
- **BCrypt Testing**: Password hashing validation included

## 🔧 Troubleshooting

### Common Java Issues
```bash
# Check Java installation
which java
java --version

# Check JAVA_HOME
echo $JAVA_HOME
ls -la $JAVA_HOME

# Fix permissions
sudo chmod -R 755 $JAVA_HOME
```

### Gradle Issues
```bash
# Check Gradle
./gradlew --version
gradle --version

# Clean and refresh dependencies
./gradlew clean build --refresh-dependencies

# Debug build issues
./gradlew build --info --stacktrace

# Clear Gradle cache
rm -rf ~/.gradle/caches
./gradlew build --refresh-dependencies
```

### Database Issues
```bash
# Check database file
ls -la ~/.solflow/
file ~/.solflow/solflow.db

# Check permissions
chmod 755 ~/.solflow/
chmod 644 ~/.solflow/solflow.db

# Reset database (deletes all users!)
rm ~/.solflow/solflow.db
```

### Application Issues
```bash
# Enable debug logging
export JAVA_OPTS="-Dorg.slf4j.simpleLogger.defaultLogLevel=debug"
./gradlew run

# Check application logs
tail -f ~/.solflow/application.log
```

## 📝 Usage

### Creating a New User
1. Launch application: `./gradlew run`
2. Click "Sign Up" on welcome screen
3. Fill in username, email, and password
4. Click "Create Account"
5. Login with new credentials

### Application Features
- **Theme Switching**: View → Dark Theme
- **User Session**: Automatic login state management
- **Logout**: User → Logout to end session
- **Password Security**: All passwords encrypted with BCrypt
- **Data Persistence**: User data saved in SQLite database

## 🏗️ Building Distribution

### Create Executable JAR
```bash
# Create fat JAR with all dependencies
./gradlew shadowJar

# JAR location
ls -la build/libs/Sol-Flow-1.0-SNAPSHOT-all.jar

# Run standalone JAR
java -jar build/libs/Sol-Flow-1.0-SNAPSHOT-all.jar
```

### System Requirements
- **Java 17+**: Runtime requirement
- **Memory**: 256MB RAM minimum
- **Storage**: 50MB for application + database
- **OS**: Windows, macOS, Linux (any OS with Java support)

## 🤝 Contributing

1. Fork the repository
2. Create feature branch: `git checkout -b feature/amazing-feature`
3. Commit changes: `git commit -m 'Add amazing feature'`
4. Push to branch: `git push origin feature/amazing-feature`
5. Open Pull Request

### Development Guidelines
- Follow Java naming conventions
- Add unit tests for new features
- Update documentation for API changes
- Use SLF4J for logging
- Follow existing code style

## 📄 License

This project is licensed under the MIT License. See the LICENSE file for details.

## 📧 Contact

- **Author**: dovndev
- **Repository**: https://github.com/dovndev/Sol-Flow
- **Issues**: https://github.com/dovndev/Sol-Flow/issues

---

**Ready to explore SolFlow? Start with `./gradlew run` and enjoy the modern Java Swing experience!**