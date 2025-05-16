import java.util.Scanner;
import java.io.IOException;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Authentication auth = new Authentication();
    private static PetManager petManager = new PetManager();
    private static AdoptionManager adoptionManager = new AdoptionManager();

    public static void main(String[] args) {
        initializeSystem();
        ConsoleUI.printWelcomeAnimation();

        boolean running = true;
        while (running) {
            ConsoleUI.printHeader("TAILMATES");
            ConsoleUI.printMenuItem(1, "Login as User");
            ConsoleUI.printMenuItem(2, "Login as Admin");
            ConsoleUI.printMenuItem(3, "Register as User");
            ConsoleUI.printMenuItem(4, "Exit");
            ConsoleUI.printPrompt("Enter your choice:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        userLogin();
                        break;
                    case 2:
                        adminLogin();
                        break;
                    case 3:
                        registerUser();
                        break;
                    case 4:
                        running = false;
                        ConsoleUI.printSuccess("Thank you for using Pet Adoption System!");
                        break;
                    default:
                        ConsoleUI.printError("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                ConsoleUI.printError("Please enter a valid number.");
            }
        }
    }

    private static void initializeSystem() {
        petManager.loadPetsFromFile();
        auth.loadUsersFromFile();
        adoptionManager.loadAdoptionsFromFile();
    }

    private static void userLogin() {
        ConsoleUI.clearScreen();
        ConsoleUI.printHeader("USER LOGIN");

        ConsoleUI.printPrompt("Enter username:");
        String username = scanner.nextLine();

        // Use masked password input
        String password = readMaskedPassword("Enter password:");

        User user = auth.authenticateUser(username, password);
        if (user != null) {
            ConsoleUI.printSuccess("Login successful! Welcome " + user.getName());
            ConsoleUI.pauseExecution();
            userMenu(user);
        } else {
            ConsoleUI.printError("Invalid username or password.");
            ConsoleUI.pauseExecution();
        }
    }

    private static void adminLogin() {
        ConsoleUI.clearScreen();
        ConsoleUI.printHeader("ADMIN LOGIN");

        ConsoleUI.printPrompt("Enter admin username:");
        String username = scanner.nextLine();

        // Use masked password input
        String password = readMaskedPassword("Enter admin password:");

        if (auth.authenticateAdmin(username, password)) {
            ConsoleUI.printSuccess("Admin login successful!");
            ConsoleUI.pauseExecution();
            adminMenu();
        } else {
            ConsoleUI.printError("Invalid admin credentials.");
            ConsoleUI.pauseExecution();
        }
    }

    private static void registerUser() {
        ConsoleUI.clearScreen();
        ConsoleUI.printHeader("USER REGISTRATION");

        // Validate name
        String name;
        do {
            ConsoleUI.printPrompt("Enter name (alphabets only):");
            name = scanner.nextLine();
            if (!InputValidator.isValidName(name)) {
                ConsoleUI.printError("Invalid name. Name must contain only alphabets and not start with a digit.");
            }
        } while (!InputValidator.isValidName(name));

        // Validate username
        String username;
        do {
            ConsoleUI.printPrompt("Enter username (alphabets only):");
            username = scanner.nextLine();
            if (!InputValidator.isValidName(username)) {
                ConsoleUI.printError(
                        "Invalid username. Username must contain only alphabets and not start with a digit.");
            }
        } while (!InputValidator.isValidName(username));

        // Get password with masking
        String password = readMaskedPassword("Enter password:");

        // Validate phone number
        String contact;
        do {
            ConsoleUI.printPrompt("Enter contact number (10 digits starting with 6, 7, 8, or 9):");
            contact = scanner.nextLine();
            if (!InputValidator.isValidPhoneNumber(contact)) {
                ConsoleUI.printError("Invalid phone number. Must be 10 digits and start with 6, 7, 8, or 9.");
            }
        } while (!InputValidator.isValidPhoneNumber(contact));

        User newUser = new User(name, username, password, contact);
        if (auth.registerUser(newUser)) {
            ConsoleUI.printSuccess("Registration successful! You can now login.");
        } else {
            ConsoleUI.printError("Username already exists. Please choose a different username.");
        }
        ConsoleUI.pauseExecution();
    }    private static void userMenu(User user) {
        boolean userSession = true;

        while (userSession) {
            ConsoleUI.clearScreen();
            ConsoleUI.printHeader("USER MENU - " + user.getName());
            ConsoleUI.printMenuItem(1, "View Available Pets");
            ConsoleUI.printMenuItem(2, "View Sorted Pets");
            ConsoleUI.printMenuItem(3, "Adopt a Pet");
            ConsoleUI.printMenuItem(4, "Add a Pet for Adoption");
            ConsoleUI.printMenuItem(5, "View My Adoption History");
            ConsoleUI.printMenuItem(6, "Logout");
            ConsoleUI.printPrompt("Enter your choice:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());                switch (choice) {
                    case 1:
                        displayAvailablePets();
                        break;
                    case 2:
                        viewSortedPets();
                        break;
                    case 3:
                        adoptPet(user);
                        break;
                    case 4:
                        addPetForAdoption();
                        break;
                    case 5:
                        viewUserAdoptionHistory(user);
                        break;
                    case 6:
                        userSession = false;
                        ConsoleUI.printSuccess("Logged out successfully.");
                        ConsoleUI.pauseExecution();
                        break;
                    default:
                        ConsoleUI.printError("Invalid choice. Please try again.");
                        ConsoleUI.pauseExecution();
                }
            } catch (NumberFormatException e) {
                ConsoleUI.printError("Please enter a valid number.");
                ConsoleUI.pauseExecution();
            }
        }
    }

    private static void adminMenu() {
        boolean adminSession = true;

        while (adminSession) {
            ConsoleUI.clearScreen();
            ConsoleUI.printHeader("ADMIN MENU");
            ConsoleUI.printMenuItem(1, "View All Available Pets");
            ConsoleUI.printMenuItem(2, "View Sorted Pets");
            ConsoleUI.printMenuItem(3, "View All Adoption Records");
            ConsoleUI.printMenuItem(4, "Remove a Pet from System");
            ConsoleUI.printMenuItem(5, "Add a Pet to System");
            ConsoleUI.printMenuItem(6, "Update Pet Details");
            ConsoleUI.printMenuItem(7, "Logout");
            ConsoleUI.printPrompt("Enter your choice:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        displayAvailablePets();
                        break;
                    case 2:
                        viewSortedPets();
                        break;
                    case 3:
                        displayAllAdoptionRecords();
                        break;
                    case 4:
                        removePet();
                        break;
                    case 5:
                        addPetForAdoption();
                        break;
                    case 6:
                        updatePetDetails();
                        break;
                    case 7:
                        adminSession = false;
                        ConsoleUI.printSuccess("Logged out successfully.");
                        ConsoleUI.pauseExecution();
                        break;
                    default:
                        ConsoleUI.printError("Invalid choice. Please try again.");
                        ConsoleUI.pauseExecution();
                }
            } catch (NumberFormatException e) {
                ConsoleUI.printError("Please enter a valid number.");
                ConsoleUI.pauseExecution();
            }
        }
    }    // Method to update pet details - Admin only
    private static void updatePetDetails() {
        ConsoleUI.clearScreen();
        ConsoleUI.printHeader("UPDATE PET DETAILS - ADMIN ONLY");

        if (petManager.getPetCount() == 0) {
            ConsoleUI.printInfo("No pets available to update.");
            ConsoleUI.pauseExecution();
            return;
        }

        // Display available pets
        Node<Pet> current = petManager.getPets().getHead();
        while (current != null) {
            ConsoleUI.printPetCard(current.getData());
            current = current.getNext();
        }

        ConsoleUI.printPrompt("Enter the ID of the pet you want to update:");
        try {
            int petId = Integer.parseInt(scanner.nextLine());
            Pet pet = petManager.getPetById(petId);

            if (pet != null) {
                ConsoleUI.clearScreen();
                ConsoleUI.printHeader("UPDATE PET: " + pet.getName());
                ConsoleUI.printPetCard(pet);

                // Update pet name
                String name;
                ConsoleUI.printInfo("Current name: " + pet.getName());
                ConsoleUI.printInfo("Press Enter to keep current name or type a new name.");
                ConsoleUI.printPrompt("Enter new pet name (alphabets only):");
                name = scanner.nextLine();
                if (!name.isEmpty()) {
                    while (!InputValidator.isValidName(name)) {
                        ConsoleUI.printError(
                                "Invalid pet name. Name must contain only alphabets and not start with a digit.");
                        ConsoleUI.printPrompt("Enter new pet name (alphabets only):");
                        name = scanner.nextLine();
                        if (name.isEmpty()) {
                            name = pet.getName(); // Keep current name if user enters empty string
                            break;
                        }
                    }
                } else {
                    name = pet.getName(); // Keep current name if user enters empty string
                }

                // Update species
                String species;
                ConsoleUI.printInfo("Current species: " + pet.getSpecies());
                ConsoleUI.printInfo("Press Enter to keep current species or type a new species.");
                ConsoleUI.printPrompt("Enter new pet species (dog/cat/bird/etc.):");
                species = scanner.nextLine();
                if (!species.isEmpty()) {
                    while (!InputValidator.isValidBreed(species)) {
                        ConsoleUI.printError(
                                "Invalid species. Species must not be a number and not start with a digit.");
                        ConsoleUI.printPrompt("Enter new pet species (dog/cat/bird/etc.):");
                        species = scanner.nextLine();
                        if (species.isEmpty()) {
                            species = pet.getSpecies(); // Keep current species if user enters empty string
                            break;
                        }
                    }
                } else {
                    species = pet.getSpecies(); // Keep current species if user enters empty string
                }

                // Update breed
                String breed;
                ConsoleUI.printInfo("Current breed: " + pet.getBreed());
                ConsoleUI.printInfo("Press Enter to keep current breed or type a new breed.");
                ConsoleUI.printPrompt("Enter new pet breed:");
                breed = scanner.nextLine();
                if (!breed.isEmpty()) {
                    while (!InputValidator.isValidBreed(breed)) {
                        ConsoleUI.printError("Invalid breed. Breed must not be a number and not start with a digit.");
                        ConsoleUI.printPrompt("Enter new pet breed:");
                        breed = scanner.nextLine();
                        if (breed.isEmpty()) {
                            breed = pet.getBreed(); // Keep current breed if user enters empty string
                            break;
                        }
                    }
                } else {
                    breed = pet.getBreed(); // Keep current breed if user enters empty string
                }

                // Update age
                int age = pet.getAge();
                ConsoleUI.printInfo("Current age: " + pet.getAge());
                ConsoleUI.printInfo("Press Enter to keep current age or type a new age.");
                ConsoleUI.printPrompt("Enter new pet age:");
                String ageInput = scanner.nextLine();
                if (!ageInput.isEmpty()) {
                    boolean validAge = false;
                    while (!validAge) {
                        try {
                            age = Integer.parseInt(ageInput);
                            if (age < 0) {
                                ConsoleUI.printError("Age cannot be negative. Please enter a valid age.");
                                ConsoleUI.printPrompt("Enter new pet age:");
                                ageInput = scanner.nextLine();
                                if (ageInput.isEmpty()) {
                                    age = pet.getAge(); // Keep current age if user enters empty string
                                    validAge = true;
                                }
                            } else {
                                validAge = true;
                            }
                        } catch (NumberFormatException e) {
                            ConsoleUI.printError("Invalid age. Please enter a valid number.");
                            ConsoleUI.printPrompt("Enter new pet age:");
                            ageInput = scanner.nextLine();
                            if (ageInput.isEmpty()) {
                                age = pet.getAge(); // Keep current age if user enters empty string
                                validAge = true;
                            }
                        }
                    }
                }

                // Update gender
                String gender = pet.getGender();
                ConsoleUI.printInfo("Current gender: " + pet.getGender());
                ConsoleUI.printInfo("Press Enter to keep current gender or type a new gender.");
                ConsoleUI.printPrompt("Enter new pet gender (Male/Female):");
                String genderInput = scanner.nextLine();
                if (!genderInput.isEmpty()) {
                    while (!InputValidator.isValidGender(genderInput)) {
                        ConsoleUI.printError("Invalid gender. Gender must be either 'Male' or 'Female'.");
                        ConsoleUI.printPrompt("Enter new pet gender (Male/Female):");
                        genderInput = scanner.nextLine();
                        if (genderInput.isEmpty()) {
                            break;
                        }
                    }
                    if (!genderInput.isEmpty()) {
                        gender = InputValidator.formatGender(genderInput);
                    }
                }

                // Update description
                ConsoleUI.printInfo("Current description: " + pet.getDescription());
                ConsoleUI.printInfo("Press Enter to keep current description or type a new description.");
                ConsoleUI.printPrompt("Enter new pet description:");
                String description = scanner.nextLine();
                if (description.isEmpty()) {
                    description = pet.getDescription(); // Keep current description if user enters empty string
                }

                // Create updated pet and update in manager
                Pet updatedPet = new Pet(pet.getId(), name, species, breed, age, gender, description);
                petManager.updatePet(updatedPet);
                ConsoleUI.printSuccess("Pet details updated successfully!");
            } else {
                ConsoleUI.printError("Pet with ID " + petId + " not found.");
            }
        } catch (NumberFormatException e) {
            ConsoleUI.printError("Please enter a valid pet ID.");
        }

        ConsoleUI.pauseExecution();
    }

    // Improved method to read password with masking
    private static String readMaskedPassword(String prompt) {
        ConsoleUI.printPrompt(prompt);

        StringBuilder password = new StringBuilder();
        char c;

        try {
            // Disable echo
            while (true) {
                // Read a single character without echoing
                c = (char) System.in.read();

                // Check for Enter key (carriage return or newline)
                if (c == '\r' || c == '\n') {
                    if (password.length() > 0) {
                        break;
                    }
                }
                // Check for backspace
                else if (c == '\b' || c == 127) {
                    if (password.length() > 0) {
                        password.deleteCharAt(password.length() - 1);
                        System.out.print("\b \b"); // Erase the last * displayed
                    }
                }
                // Regular character
                else if (c != '\n' && c != '\r') {
                    password.append(c);
                    System.out.print("*"); // Print * for each character
                }
            }
            System.out.println(); // Move to next line after password input
        } catch (IOException e) {
            // In case of any error, fall back to regular input
            ConsoleUI.printError("Password masking not available. Please enter your password:");
            return scanner.nextLine();
        }

        return password.toString();
    }

    // Display available pets
    private static void displayAvailablePets() {
        ConsoleUI.clearScreen();
        ConsoleUI.printHeader("AVAILABLE PETS");

        if (petManager.getPetCount() == 0) {
            ConsoleUI.printInfo("No pets available for adoption.");
            ConsoleUI.pauseExecution();
            return;
        }

        Node<Pet> current = petManager.getPets().getHead();
        while (current != null) {
            ConsoleUI.printPetCard(current.getData());
            current = current.getNext();
        }

        ConsoleUI.pauseExecution();
    }

    // View sorted pets
    private static void viewSortedPets() {
        ConsoleUI.clearScreen();
        ConsoleUI.printHeader("SORT PETS");
        ConsoleUI.printMenuItem(1, "Sort by ID");
        ConsoleUI.printMenuItem(2, "Sort by Name");
        ConsoleUI.printMenuItem(3, "Sort by Age");
        ConsoleUI.printMenuItem(4, "Sort by Species");
        ConsoleUI.printPrompt("Enter your choice:");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            PetSorter.SortCriteria criteria;

            switch (choice) {
                case 1:
                    criteria = PetSorter.SortCriteria.ID;
                    break;
                case 2:
                    criteria = PetSorter.SortCriteria.NAME;
                    break;
                case 3:
                    criteria = PetSorter.SortCriteria.AGE;
                    break;
                case 4:
                    criteria = PetSorter.SortCriteria.SPECIES;
                    break;
                default:
                    ConsoleUI.printError("Invalid choice. Defaulting to sort by ID.");
                    criteria = PetSorter.SortCriteria.ID;
            }

            displaySortedPets(criteria);

        } catch (NumberFormatException e) {
            ConsoleUI.printError("Invalid input. Please enter a number.");
            ConsoleUI.pauseExecution();
        }
    }

    // Display sorted pets
    private static void displaySortedPets(PetSorter.SortCriteria criteria) {
        ConsoleUI.clearScreen();
        ConsoleUI.printHeader("SORTED PETS BY " + criteria.toString());

        if (petManager.getPetCount() == 0) {
            ConsoleUI.printInfo("No pets available for adoption.");
            ConsoleUI.pauseExecution();
            return;
        }

        CustomLinkedList<Pet> sortedPets = PetSorter.binaryInsertionSort(petManager.getPets(), criteria);

        Node<Pet> current = sortedPets.getHead();
        while (current != null) {
            ConsoleUI.printPetCard(current.getData());
            current = current.getNext();
        }

        ConsoleUI.pauseExecution();
    }

    // View user adoption history
    private static void viewUserAdoptionHistory(User user) {
        ConsoleUI.clearScreen();
        ConsoleUI.printHeader("YOUR ADOPTION HISTORY");

        boolean found = false;
        Node<AdoptionRecord> current = adoptionManager.getAdoptionRecords().getHead();

        while (current != null) {
            if (current.getData().getAdopterUsername().equals(user.getUsername())) {
                ConsoleUI.printAdoptionRecord(current.getData());
                found = true;
            }
            current = current.getNext();
        }

        if (!found) {
            ConsoleUI.printInfo("You have not adopted any pets yet.");
        }

        ConsoleUI.pauseExecution();
    }

    // Display all adoption records
    private static void displayAllAdoptionRecords() {
        ConsoleUI.clearScreen();
        ConsoleUI.printHeader("ALL ADOPTION RECORDS");

        if (adoptionManager.getAdoptionRecords().isEmpty()) {
            ConsoleUI.printInfo("No adoption records found.");
            ConsoleUI.pauseExecution();
            return;
        }

        Node<AdoptionRecord> current = adoptionManager.getAdoptionRecords().getHead();
        while (current != null) {
            ConsoleUI.printAdoptionRecord(current.getData());
            current = current.getNext();
        }

        ConsoleUI.pauseExecution();
    }

    // Adopt a pet
    private static void adoptPet(User user) {
        ConsoleUI.clearScreen();
        ConsoleUI.printHeader("ADOPT A PET");

        displayAvailablePets();

        if (petManager.getPetCount() == 0) {
            return; // Already showed message in displayAvailablePets
        }

        ConsoleUI.printPrompt("Enter the ID of the pet you want to adopt:");
        try {
            int petId = Integer.parseInt(scanner.nextLine());
            Pet pet = petManager.getPetById(petId);

            if (pet != null) {
                ConsoleUI.clearScreen();
                ConsoleUI.printHeader("CONFIRM ADOPTION");
                ConsoleUI.printPetCard(pet);
                ConsoleUI.printInfo("You are adopting: " + pet.getName() + " (" + pet.getSpecies() + ")");
                ConsoleUI.printPrompt("Confirm adoption (y/n):");
                String confirm = scanner.nextLine();

                if (confirm.equalsIgnoreCase("y")) {
                    AdoptionRecord record = new AdoptionRecord(pet, user.getUsername(), user.getName(),
                            user.getContact());
                    adoptionManager.addAdoptionRecord(record);
                    petManager.removePet(petId);
                    ConsoleUI.printSuccess("Congratulations! You have successfully adopted " + pet.getName() + ".");
                } else {
                    ConsoleUI.printInfo("Adoption cancelled.");
                }
            } else {
                ConsoleUI.printError("Pet with ID " + petId + " not found.");
            }
        } catch (NumberFormatException e) {
            ConsoleUI.printError("Please enter a valid pet ID.");
        }

        ConsoleUI.pauseExecution();
    }

    // Add a pet for adoption
    private static void addPetForAdoption() {
        ConsoleUI.clearScreen();
        ConsoleUI.printHeader("ADD A PET FOR ADOPTION");

        // Validate pet name
        String name;
        do {
            ConsoleUI.printPrompt("Enter pet name (alphabets only):");
            name = scanner.nextLine();
            if (!InputValidator.isValidName(name)) {
                ConsoleUI.printError("Invalid pet name. Name must contain only alphabets and not start with a digit.");
            }
        } while (!InputValidator.isValidName(name));

        // Validate species
        String species;
        do {
            ConsoleUI.printPrompt("Enter pet species (dog/cat/bird/etc.):");
            species = scanner.nextLine();
            if (!InputValidator.isValidBreed(species)) {
                ConsoleUI.printError("Invalid species. Species must not be a number and not start with a digit.");
            }
        } while (!InputValidator.isValidBreed(species));

        // Validate breed
        String breed;
        do {
            ConsoleUI.printPrompt("Enter pet breed:");
            breed = scanner.nextLine();
            if (!InputValidator.isValidBreed(breed)) {
                ConsoleUI.printError("Invalid breed. Breed must not be a number and not start with a digit.");
            }
        } while (!InputValidator.isValidBreed(breed));

        // Validate age
        int age = 0;
        boolean validAge = false;
        do {
            ConsoleUI.printPrompt("Enter pet age:");
            try {
                age = Integer.parseInt(scanner.nextLine());
                if (age < 0) {
                    ConsoleUI.printError("Age cannot be negative. Please enter a valid age.");
                } else {
                    validAge = true;
                }
            } catch (NumberFormatException e) {
                ConsoleUI.printError("Invalid age. Please enter a valid number.");
            }
        } while (!validAge);

        // Validate gender
        String gender;
        do {
            ConsoleUI.printPrompt("Enter pet gender (Male/Female):");
            gender = scanner.nextLine();
            if (!InputValidator.isValidGender(gender)) {
                ConsoleUI.printError("Invalid gender. Gender must be either 'Male' or 'Female'.");
            }
        } while (!InputValidator.isValidGender(gender));

        // Format gender properly
        gender = InputValidator.formatGender(gender);

        ConsoleUI.printPrompt("Enter pet description:");
        String description = scanner.nextLine();

        Pet newPet = new Pet(petManager.getNextPetId(), name, species, breed, age, gender, description);
        petManager.addPet(newPet);
        ConsoleUI.printSuccess("Pet added successfully!");

        ConsoleUI.pauseExecution();
    }

    // Remove a pet
    private static void removePet() {
        ConsoleUI.clearScreen();
        ConsoleUI.printHeader("REMOVE A PET");

        displayAvailablePets();

        if (petManager.getPetCount() == 0) {
            return; // Already showed message in displayAvailablePets
        }

        ConsoleUI.printPrompt("Enter the ID of the pet you want to remove:");
        try {
            int petId = Integer.parseInt(scanner.nextLine());
            if (petManager.removePet(petId)) {
                ConsoleUI.printSuccess("Pet removed successfully.");
            } else {
                ConsoleUI.printError("Pet with ID " + petId + " not found.");
            }
        } catch (NumberFormatException e) {
            ConsoleUI.printError("Please enter a valid pet ID.");
        }

        ConsoleUI.pauseExecution();
    }
}