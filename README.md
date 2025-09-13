# SolFlow Application

SolFlow is a modern Java Swing application with SQL database support for user authentication.

## Features

### 🔐 Authentication & Security
- **SQL Database Storage**: SQLite database for persistent user data
- **BCrypt Password Hashing**: Industry-standard password security
- **Email & Username Support**: Login with either username or email
- **Input Validation**: Comprehensive validation for all user inputs
- **Session Management**: Secure user session tracking

### 🎨 User Interface
- **Modern UI**: FlatLaf look-and-feel with light/dark theme support
- **Theme Switching**: Toggle between light and dark modes
- **Responsive Design**: Clean, modern interface with proper error handling
- **Menu System**: Complete menu bar with user and database management

### 📊 Database Features
- **SQLite Integration**: Embedded database with automatic schema creation
- **User Repository Pattern**: Clean separation of data access logic
- **Database Info**: View user statistics and database information
- **Persistent Settings**: Theme preferences stored securely

## Dependencies

### Core Framework
- **Java 17**: Modern Java runtime
- **Gradle 8+**: Build automation and dependency management
- **Shadow Plugin**: Fat JAR creation for distribution

### User Interface
- **FlatLaf 3.2.5**: Modern look-and-feel for Swing applications
- **SwingX 1.6.5**: Enhanced Swing components

### Database & Persistence
- **SQLite JDBC 3.43.0**: Embedded SQL database driver
- **H2 Database 2.2.224**: Alternative database engine
- **Gson 2.10.1**: JSON serialization for settings

### Security & Logging
- **BCrypt 0.10.2**: Secure password hashing library
- **SLF4J 2.0.13**: Logging facade and simple implementation

### Testing
- **JUnit Jupiter 5.9.2**: Modern unit testing framework

## Setup Instructions

### 1. Prerequisites
- Java 17 or higher installed
- JAVA_HOME environment variable set correctly

### 2. Clone and Build
```bash
git clone https://github.com/dovndev/Sol-Flow.git
cd Sol-Flow/solflow
chmod +x gradlew
./gradlew clean build
```

### 3. Run the Application
```bash
# Development mode
./gradlew run

# Or build and run fat JAR
./gradlew shadowJar
java -jar build/libs/solflow.jar
```

## Usage

### User Registration
1. Click "Sign Up" from the home screen
2. Fill in username (3-20 chars), email, and password (6+ chars)
3. Passwords must match
4. Account stored in SQLite database with BCrypt hashing

### User Login
1. Click "Login" from the home screen
2. Enter username/email and password
3. Successful login shows personalized home screen

### Application Features
- **Theme Switching**: View menu → toggle light/dark themes
- **User Management**: User menu → logout functionality
- **Database Info**: Database menu → view user statistics

## Configuration

- **Database**: Auto-created at `~/.solflow/solflow.db`
- **Settings**: Theme preferences at `~/.solflow/settings.json`
- **Logs**: Console output with configurable SLF4J levels

## Development

### Building for Distribution
```bash
./gradlew shadowJar
# Output: build/libs/solflow.jar
```

### Running Tests
```bash
./gradlew test
```

### Database Schema
```sql
CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## License

This project is licensed under the MIT License.

---

**Built with ❤️ using Java 17, Swing, SQLite, and modern development practices.**











SETUP


## Environment Setup Guide

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
# Make wrapper executable
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

#### H2 Database (Alternative)
```bash
# H2 Console (optional for debugging)
# Add to build.gradle dependencies:
# runtimeOnly 'com.h2database:h2'

# Access H2 Console at: http://localhost:8082
# JDBC URL: jdbc:h2:~/.solflow/solflow
```

### Troubleshooting Environment Issues

#### Java Issues
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

#### Gradle Issues
```bash
# Check Gradle
./gradlew --version
gradle --version

# Clean and refresh dependencies
./gradlew clean build --refresh-dependencies

# Debug build issues
./gradlew build --info --stacktrace
```

#### Common Solutions
1. **"JAVA_HOME not set"**: Set JAVA_HOME environment variable
2. **"Permission denied"**: `chmod +x gradlew`
3. **"Could not find Java"**: Add Java to PATH
4. **Dependencies not downloading**: Check internet connection, clear Gradle cache:
   ```bash
   rm -rf ~/.gradle/caches
   ./gradlew build --refresh-dependencies
   ```

### Development Workflow
```bash
# 1. Clone and setup
git clone https://github.com/dovndev/Sol-Flow.git
cd Sol-Flow/solflow

# 2. Verify environment
java --version
./gradlew --version

# 3. Build and test
./gradlew clean build test

# 4. Run application
./gradlew run

# 5. Create distribution
./gradlew shadowJar
```
```
