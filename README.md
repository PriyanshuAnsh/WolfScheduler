# Wolf Scheduler 🐺📅
 ## Welcome

### Overview
 Wolf Scheduler is a comprehensive course and event scheduling application designed to help students and administrators manage academic schedules efficiently. Built with Java, this robust application provides a user-friendly graphical interface for creating, modifying, and managing schedules.

### 🌟 Key Features
#### Course Management
📚 Load courses from external files
➕ Add and remove courses from schedules
✅ Validate course conflicts
🔢 Support for multiple course sections
Event Scheduling
📅 Create custom events
📋 Manage personal and academic events
🚫 Conflict detection between events and courses
User Interface
🖥️ Intuitive graphical interface
🧭 Easy navigation between schedule views
🔄 Real-time schedule modification
🛠 Technologies Used
JavaSwingJUnit

Java
Swing for GUI
JUnit for testing
File I/O for data persistence
🚀 Getting Started
Prerequisites
Java Development Kit (JDK) 11+
IDE with Java support (Eclipse, IntelliJ IDEA)
Installation
Clone the repository
bash

Verify

Open In Editor
Edit
Copy code
git clone https://github.com/your-username/wolf-scheduler.git
Open the project in your preferred Java IDE
Compile and run the WolfSchedulerGUI class
Quick Start
bash

Verify

Open In Editor
Edit
Copy code
# Compile the project
javac src/edu/ncsu/csc216/wolf_scheduler/**/*.java

# Run the application
java -cp src edu.ncsu.csc216.wolf_scheduler.ui.WolfSchedulerGUI
📂 Project Structure

Verify

Open In Editor
Edit
Copy code
wolf-scheduler/
├── .github/                # GitHub workflow configurations
├── src/                    # Source code
│   └── edu/ncsu/csc216/wolf_scheduler/
│       ├── course/         # Course and event models
│       ├── io/             # File input/output operations
│       ├── scheduler/      # Schedule management logic
│       └── ui/             # Graphical user interface
├── test/                   # Unit tests
├── resources/              # Additional resources
└── README.md               # Project documentation
🧪 Testing
The project includes comprehensive unit tests to ensure reliability:

Course validation tests
Event conflict detection
Schedule manipulation tests
