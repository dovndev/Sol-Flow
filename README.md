# Sol-Flow - Modern Java Swing Application

A beautiful and modern Java Swing application with a clean, professional user interface.

## Features

- **Modern UI Design**: Clean and intuitive user interface with support for light and dark themes
- **Project Explorer**: File tree navigation with project structure visualization
- **Tabbed Interface**: Multi-tab content area for different views and editors
- **Status Bar**: Real-time status updates, memory usage, and time display
- **Menu System**: Comprehensive menu system with file operations and settings
- **Theme Support**: Toggle between light and dark modes
- **Extensible Architecture**: Well-organized codebase for easy extension

## Screenshots

The application includes:
- Welcome tab with getting started information
- Code editor tab for text editing
- Data view tab with table display
- Charts tab with visual representations

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

## Building and Running

### Using Maven

1. **Clone the repository:**
   ```bash
   git clone <your-repo-url>
   cd Sol-Flow
   ```

2. **Compile the project:**
   ```bash
   mvn compile
   ```

3. **Run the application:**
   ```bash
   mvn exec:java
   ```

4. **Build executable JAR:**
   ```bash
   mvn package
   ```

5. **Run the JAR file:**
   ```bash
   java -jar target/sol-flow-1.0.0.jar
   ```

### Development

The project uses Maven for dependency management and build automation. The main class is `com.solflow.SolFlowApplication`.

**Project Structure:**
```
src/
├── main/
│   └── java/
│       └── com/
│           └── solflow/
│               ├── SolFlowApplication.java    # Main application class
│               ├── ui/
│               │   ├── MainFrame.java         # Main window
│               │   └── components/
│               │       ├── SidePanel.java     # Project explorer
│               │       ├── MainContentPanel.java # Content area
│               │       └── StatusBar.java     # Status bar
│               └── util/
│                   └── ThemeManager.java      # Theme management
└── test/
    └── java/
        └── com/
            └── solflow/
                └── # Test classes
```

## Dependencies

- **FlatLaf**: Modern look and feel for Swing applications
- **JUnit Jupiter**: Testing framework

## Usage

### Getting Started

1. Launch the application
2. Use the File menu to create or open projects
3. Navigate through the project structure using the side panel
4. Switch between different views using the tabbed interface
5. Toggle between light and dark themes in the View menu

### Key Features

- **File Operations**: Create, open, and save projects
- **Project Navigation**: Browse project files in the tree view
- **Multi-tab Interface**: Work with multiple documents simultaneously
- **Theme Switching**: Choose between light and dark modes
- **Real-time Status**: Monitor application status and memory usage

## Customization

The application is designed to be easily extensible:

1. **Adding New Tabs**: Use `MainContentPanel.addNewTab()` to add custom content
2. **Custom Themes**: Extend `ThemeManager` to add new color schemes
3. **New Components**: Add components to the `ui.components` package
4. **Menu Extensions**: Modify `MainFrame.createMenuBar()` to add new menu items

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For support and questions, please open an issue in the GitHub repository.

---

**Sol-Flow** - Building beautiful Java applications with modern design principles.
