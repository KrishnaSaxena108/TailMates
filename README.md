# TailMates 🐾

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Console](https://img.shields.io/badge/Console-Application-blue?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Complete-success?style=for-the-badge)

<div align="center">
  <h3>🎓 A Java-based console application for pet adoption management</h3>
  <p><em>Built as a college project demonstrating custom data structures implementation</em></p>
</div>

---

## 📚 Table of Contents

- [🎯 About the Project](#-about-the-project)  
- [✨ Features](#-features)  
- [🚀 Getting Started](#-getting-started)  
- [💻 Usage](#-usage)  
- [📁 Project Structure](#-project-structure)  
- [🏗️ Data Structures Used](#️-data-structures-used)  
- [📖 What I Learned](#-what-i-learned)  
- [🔮 Future Improvements](#-future-improvements)  

---

## 🎯 About the Project

<div align="center">
  <img src="https://img.icons8.com/emoji/96/000000/dog-face.png" alt="Dog" width="80"/>
  <img src="https://img.icons8.com/emoji/96/000000/cat-face.png" alt="Cat" width="80"/>
</div>

**TailMates** is a console-based pet adoption management system developed as part of my **Data Structures and Algorithms** course. The project showcases practical implementation of various data structures and demonstrates object-oriented programming principles in Java.

| 📋 **Project Details** |  |
|------------------------|--|
| **Course** | Data Structures and Algorithms |
| **Language** | Java ☕ |
| **Type** | Console Application 💻 |
| **Focus** | Custom Data Structure Implementation 🔧 |

---

## ✨ Features

### 🔐 **Core Functionality**
- 👤 **User Authentication**: Simple login and registration system
- 🐕 **Pet Management**: Add, view, update, and delete pet records
- 📊 **Adoption Tracking**: Record and track pet adoptions
- 🔍 **Search & Sort**: Find pets and sort by different criteria
- ✅ **Data Validation**: Input validation for user entries

### 🛠️ **Data Structures Implemented**
- 🔗 **Custom Linked List** for storing user and pet data
- 🌳 **Binary Search Tree (BST)** for efficient pet searching
- 📈 **Various sorting algorithms** for data organization

---

## 🚀 Getting Started

### 📋 Prerequisites

- ☕ **Java Development Kit (JDK) 8** or higher
- 🖥️ Any text editor or IDE (**VS Code**, **Eclipse**, **IntelliJ**)

### 💾 Installation

```bash
# 1️⃣ Clone the repository
git clone https://github.com/KrishnaSaxena108/TailMates.git

# 2️⃣ Navigate to project directory
cd TailMates

# 3️⃣ Compile the Java files
javac *.java

# 4️⃣ Run the application
java Main
```

---

## 💻 Usage

### 🎮 How to Use

1. **🚀 Start the application** - Run `java Main`
2. **🔑 Login/Register** - Create an account or login with existing credentials
3. **📋 Choose from Main Menu Options**:
   - 📖 View all pets available for adoption
   - ➕ Add new pets to the system
   - ✏️ Update existing pet information
   - 🗑️ Delete pets from records
   - 🔍 Search for specific pets
   - 📊 Sort pets by name, age, or type
   - 🏠 Record pet adoptions
   - 📜 View adoption history

### 🖥️ Sample Run:

```console
┌─────────────────────────────────────┐
│     🐾 TailMates Pet Adoption 🐾     │
│          Management System          │
└─────────────────────────────────────┘

1. 🔑 Login
2. 📝 Register
3. 🚪 Exit

Choose option: 1

👤 Username: student
🔒 Password: pass123

✅ Welcome back, student!

┌─────────────────────────────────────┐
│            📋 Main Menu             │
└─────────────────────────────────────┘
1. 👀 View All Pets
2. ➕ Add New Pet
3. 📊 Sort Pets
4. 🏠 Record Adoption
5. 🚪 Logout

Choose option: 2

🐕 Enter pet name: Buddy
🐾 Enter pet type: Dog
🦴 Enter breed: Golden Retriever
🎂 Enter age: 3

✅ Pet added successfully!
```

---

## 📁 Project Structure

```
TailMates/
├── 🚀 Main.java                 # Entry point of the application
├── 🖥️ ConsoleUI.java           # User interface and menu handling
├── 🔐 Authentication.java       # User login/registration
├── ✅ InputValidator.java       # Input validation methods
├── 🐕 Pet.java                 # Pet class with attributes
├── 👤 User.java                # User class
├── 🔗 CustomLinkedList.java    # Custom linked list implementation
├── 🔘 Node.java                # Node class for linked list
├── 🌳 PetBST.java             # Binary Search Tree for pets
├── 🛠️ PetManager.java         # Pet CRUD operations
├── 📋 AdoptionRecord.java     # Adoption record model
├── 📊 AdoptionManager.java    # Adoption management
└── 📈 PetSorter.java          # Sorting utilities
```

### 🔑 Key Files:

| 📄 **File** | 🎯 **Purpose** |
|-------------|----------------|
| `Main.java` | 🚀 Program entry point and main flow |
| `ConsoleUI.java` | 🖥️ Handles all console input/output |
| `Pet.java` | 🐾 Pet model (name, type, breed, age, etc.) |
| `CustomLinkedList.java` | 🔗 My implementation of linked list |
| `PetBST.java` | 🌳 Binary search tree for efficient pet storage |
| `PetManager.java` | 🛠️ Business logic for pet operations |
| `PetSorter.java` | 📈 Sorting algorithms implementation |

---

## 🏗️ Data Structures Used

### 1️⃣ **Custom Linked List** 🔗
- **🎯 Purpose**: Store pets and users in insertion order
- **⚡ Operations**: Add, remove, search, display
- **⏱️ Time Complexity**: `O(n)` for search, `O(1)` for insertion at head

### 2️⃣ **Binary Search Tree (BST)** 🌳
- **🎯 Purpose**: Efficient searching and sorting of pets by name
- **⚡ Operations**: Insert, search, delete, in-order traversal
- **⏱️ Time Complexity**: `O(log n)` average case for search/insert/delete

### 3️⃣ **Arrays** 📊
- **🎯 Purpose**: Menu options and temporary data storage
- **💡 Used for**: Static data that doesn't change frequently

---

## 📖 What I Learned

<div align="center">
  <img src="https://img.icons8.com/color/96/000000/graduation-cap.png" alt="Learning" width="80"/>
</div>

Through this project, I gained hands-on experience with:

### 🧠 **Technical Skills**
- **🏗️ Data Structures**: Implementing linked lists and BSTs from scratch
- **🎯 Object-Oriented Programming**: Creating classes, encapsulation, inheritance
- **✅ Input Validation**: Handling user input errors gracefully
- **📁 File Organization**: Structuring a multi-class Java project
- **🧩 Problem Solving**: Breaking down complex problems into smaller functions
- **📈 Algorithm Implementation**: Sorting and searching algorithms
- **🖥️ Console Application Design**: Creating user-friendly command-line interfaces

### 💪 **Challenges Faced**:
- 🌳 Implementing BST deletion with three cases (no child, one child, two children)
- 🔗 Managing memory and references in linked list operations
- 🎨 Designing intuitive console menus
- 🛡️ Handling various edge cases in user input

---

## 🔮 Future Improvements

If I were to extend this project, I would add:

- [ ] 🔍 More advanced search filters
- [ ] 📚 Pet categories and breeds database
- [ ] 📊 Adoption statistics and reports
- [ ] 🖼️ GUI interface using Swing
- [ ] 🗄️ Integration with a database (MySQL)
- [ ] 🧪 Unit tests for all classes
- [ ] ⚠️ Exception handling improvements

---

## 🧪 How to Test

1. **➕ Add some pets** using the "Add Pet" option
2. **🔍 Try searching** for pets by name
3. **📊 Test sorting** by different criteria
4. **🏠 Record an adoption** and check adoption history
5. **❌ Try invalid inputs** to test validation

---

## ✅ Assignment Requirements Met

| Requirement | Status | Description |
|------------|--------|-------------|
| **Custom Data Structures** | ✅ | Linked List and BST implemented from scratch |
| **Object-Oriented Design** | ✅ | Multiple classes with proper encapsulation |
| **User Input Handling** | ✅ | Console-based interface with validation |
| **CRUD Operations** | ✅ | Create, Read, Update, Delete functionality |
| **Sorting Algorithms** | ✅ | Multiple sorting options implemented |
| **Search Functionality** | ✅ | Efficient searching using BST |
| **Code Documentation** | ✅ | Comments and clear method names |

---

## 👨‍💻 Team

<div align="center">
  <table>
    <tr>
      <td align="center">
        <a href="https://github.com/KrishnaSaxena108">
          <img src="https://img.icons8.com/color/96/000000/developer.png" width="100px;" alt="Krishna"/><br />
          <sub><b>Krishna Saxena</b></sub>
        </a><br />
        <sub>🎓 Lead Developer</sub>
      </td>
      <td align="center">
        <a href="https://github.com/Spydiecy">
          <img src="https://img.icons8.com/color/96/000000/collaboration.png" width="100px;" alt="Contributor"/><br />
          <sub><b>Spydiecy</b></sub>
        </a><br />
        <sub>🤝 Contributor</sub>
      </td>
    </tr>
  </table>
</div>

---

## 🙏 Acknowledgments

<div align="center">
  <img src="https://img.icons8.com/color/48/000000/thank-you.png" alt="Thank you"/>
</div>

- 👨‍🏫 **Course instructor** for project guidance
- 👥 **Classmates** for testing, feedback and contribution
- 📚 **Online Java documentation** and tutorials
- 🌐 **Stack Overflow community** for debugging help

---

<div align="center">
  <h3>🎓 Academic Project</h3>
  <p><em>This project was completed as part of my Computer Science coursework to demonstrate understanding of data structures and algorithms in Java.</em></p>
  
  <br/>
  
  <img src="https://img.icons8.com/color/48/000000/java-coffee-cup-logo.png" alt="Java"/>
  <img src="https://img.icons8.com/color/48/000000/console.png" alt="Console"/>
  
  <br/><br/>
  
  **⭐ Star this repo if you found it helpful!**
  
  <br/>
  
  Made with ❤️ for pets and their future families 🐾
</div>
