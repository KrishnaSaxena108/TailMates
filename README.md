# TailMates 🐾

A Java-based console application for pet adoption management built as a college project. This project demonstrates the implementation of custom data structures like linked lists and binary search trees in a practical application.

---

## Table of Contents

- [About the Project](#about-the-project)  
- [Features](#features)  
- [Getting Started](#getting-started)  
- [Usage](#usage)  
- [Project Structure](#project-structure)  
- [Data Structures Used](#data-structures-used)  
- [What I Learned](#what-i-learned)  
- [Future Improvements](#future-improvements)  

---

## About the Project

TailMates is a console-based pet adoption management system developed as part of my Data Structures and Algorithms course. The project showcases practical implementation of various data structures and demonstrates object-oriented programming principles in Java.

**Course**: Data Structures and Algorithms  
**Language**: Java  
**Type**: Console Application  
**Focus**: Custom data structure implementation  

---

## Features

- **User Authentication**: Simple login and registration system
- **Pet Management**: Add, view, update, and delete pet records
- **Adoption Tracking**: Record and track pet adoptions
- **Search & Sort**: Find pets and sort by different criteria
- **Data Validation**: Input validation for user entries

**Data Structures Implemented**:
- Custom Linked List for storing user and pet data
- Binary Search Tree (BST) for efficient pet searching
- Various sorting algorithms

---

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Any text editor or IDE (VS Code, Eclipse, IntelliJ)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/KrishnaSaxena108/TailMates.git
   ```

2. Navigate to project directory:
   ```bash
   cd TailMates
   ```

3. Compile the Java files:
   ```bash
   javac *.java
   ```

4. Run the application:
   ```bash
   java Main
   ```

---

## Usage

1. **Start the application** - Run `java Main`
2. **Login/Register** - Create an account or login with existing credentials
3. **Main Menu Options**:
   - View all pets available for adoption
   - Add new pets to the system
   - Update existing pet information
   - Delete pets from records
   - Search for specific pets
   - Sort pets by name, age, or type
   - Record pet adoptions
   - View adoption history

### Sample Run:
```
=== TailMates Pet Adoption System ===

1. Login
2. Register
3. Exit

Choose option: 1

Username: student
Password: pass123

=== Main Menu ===
1. View All Pets
2. Add New Pet
3. Sort Pets
4. Record Adoption
5. Logout

Choose option: 2

Enter pet name: Buddy
Enter pet type: Dog
Enter breed: Golden Retriever
Enter age: 3

Pet added successfully!
```

---

## Project Structure

```
TailMates/
├── Main.java                 # Entry point of the application
├── ConsoleUI.java           # User interface and menu handling
├── Authentication.java       # User login/registration
├── InputValidator.java       # Input validation methods
├── Pet.java                 # Pet class with attributes
├── User.java                # User class
├── CustomLinkedList.java    # Custom linked list implementation
├── Node.java                # Node class for linked list
├── PetBST.java             # Binary Search Tree for pets
├── PetManager.java         # Pet CRUD operations
├── AdoptionRecord.java     # Adoption record model
├── AdoptionManager.java    # Adoption management
└── PetSorter.java          # Sorting utilities
```

### Key Files:

| File | Purpose |
|------|---------|
| `Main.java` | Program entry point and main flow |
| `ConsoleUI.java` | Handles all console input/output |
| `Pet.java` | Pet model (name, type, breed, age, etc.) |
| `CustomLinkedList.java` | My implementation of linked list |
| `PetBST.java` | Binary search tree for efficient pet storage |
| `PetManager.java` | Business logic for pet operations |
| `PetSorter.java` | Sorting algorithms implementation |

---

## Data Structures Used

### 1. Custom Linked List
- **Purpose**: Store pets and users in insertion order
- **Operations**: Add, remove, search, display
- **Time Complexity**: O(n) for search, O(1) for insertion at head

### 2. Binary Search Tree (BST)
- **Purpose**: Efficient searching and sorting of pets by name
- **Operations**: Insert, search, delete, in-order traversal
- **Time Complexity**: O(log n) average case for search/insert/delete

### 3. Arrays
- **Purpose**: Menu options and temporary data storage
- **Used for**: Static data that doesn't change frequently

---

## What I Learned

Through this project, I gained hands-on experience with:

- **Data Structures**: Implementing linked lists and BSTs from scratch
- **Object-Oriented Programming**: Creating classes, encapsulation, inheritance
- **Input Validation**: Handling user input errors gracefully
- **File Organization**: Structuring a multi-class Java project
- **Problem Solving**: Breaking down complex problems into smaller functions
- **Algorithm Implementation**: Sorting and searching algorithms
- **Console Application Design**: Creating user-friendly command-line interfaces

### Challenges Faced:
- Implementing BST deletion with three cases (no child, one child, two children)
- Managing memory and references in linked list operations
- Designing intuitive console menus
- Handling various edge cases in user input

---

## Future Improvements

If I were to extend this project, I would add:

- [ ] More advanced search filters
- [ ] Pet categories and breeds database
- [ ] Adoption statistics and reports
- [ ] GUI interface using Swing
- [ ] Integration with a database (MySQL)
- [ ] Unit tests for all classes
- [ ] Exception handling improvements

---

## How to Test

1. **Add some pets** using the "Add Pet" option
2. **Try searching** for pets by name
3. **Test sorting** by different criteria
4. **Record an adoption** and check adoption history
5. **Try invalid inputs** to test validation

---

## Assignment Requirements Met

✅ **Custom Data Structures**: Linked List and BST implemented from scratch  
✅ **Object-Oriented Design**: Multiple classes with proper encapsulation  
✅ **User Input Handling**: Console-based interface with validation  
✅ **CRUD Operations**: Create, Read, Update, Delete functionality  
✅ **Sorting Algorithms**: Multiple sorting options implemented  
✅ **Search Functionality**: Efficient searching using BST  
✅ **Code Documentation**: Comments and clear method names  

---

## Author

**Krishna Saxena**  
GitHub: [@KrishnaSaxena108](https://github.com/KrishnaSaxena108)
---

---

## Contributors
Github: [@Spydiecy](https://github.com/Spydiecy)
---

## Acknowledgments

- Course instructor for project guidance
- Classmates for testing and feedback and contribution
- Online Java documentation and tutorials
- Stack Overflow community for debugging help

---

*This project was completed as part of my Computer Science coursework to demonstrate understanding of data structures and algorithms in Java.*
