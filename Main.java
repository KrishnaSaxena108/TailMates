import java.util.Scanner;

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
            ConsoleUI.printHeader("PET ADOPTION SYSTEM");
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
        ConsoleUI.printPrompt("Enter password:");
        String password = scanner.nextLine();

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
        ConsoleUI.printPrompt("Enter admin password:");
        String password = scanner.nextLine();

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
        
        ConsoleUI.printPrompt("Enter name:");
        String name = scanner.nextLine();
        ConsoleUI.printPrompt("Enter username:");
        String username = scanner.nextLine();
        ConsoleUI.printPrompt("Enter password:");
        String password = scanner.nextLine();
        ConsoleUI.printPrompt("Enter contact number:");
        String contact = scanner.nextLine();

        User newUser = new User(name, username, password, contact);
        if (auth.registerUser(newUser)) {
            ConsoleUI.printSuccess("Registration successful! You can now login.");
        } else {
            ConsoleUI.printError("Username already exists. Please choose a different username.");
        }
        ConsoleUI.pauseExecution();
    }

    private static void userMenu(User user) {
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
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
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
            ConsoleUI.printMenuItem(6, "Logout");
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
    }

    // Improved method to display available pets
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

    // Improved method for viewing sorted pets
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

    // Helper method to display sorted pets
    private static void displaySortedPets(PetSorter.SortCriteria criteria) {
        ConsoleUI.clearScreen();
        ConsoleUI.printHeader("SORTED PETS BY " + criteria.toString());
        
        if (petManager.getPetCount() == 0) {
            ConsoleUI.printInfo("No pets available for adoption.");
            ConsoleUI.pauseExecution();
            return;
        }

        // Get sorted list using binary insertion sort
        CustomLinkedList<Pet> sortedPets = PetSorter.binaryInsertionSort(petManager.getPets(), criteria);
        
        Node<Pet> current = sortedPets.getHead();
        while (current != null) {
            ConsoleUI.printPetCard(current.getData());
            current = current.getNext();
        }
        
        ConsoleUI.pauseExecution();
    }

    // Improved method to view user adoption history
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

    // Improved method to display all adoption records (admin only)
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

    private static void addPetForAdoption() {
        ConsoleUI.clearScreen();
        ConsoleUI.printHeader("ADD A PET FOR ADOPTION");
        
        ConsoleUI.printPrompt("Enter pet name:");
        String name = scanner.nextLine();
        ConsoleUI.printPrompt("Enter pet species (dog/cat/bird/etc.):");
        String species = scanner.nextLine();
        ConsoleUI.printPrompt("Enter pet breed:");
        String breed = scanner.nextLine();
        ConsoleUI.printPrompt("Enter pet age:");
        int age = 0;
        try {
            age = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            ConsoleUI.printError("Invalid age. Setting age to 0.");
        }
        ConsoleUI.printPrompt("Enter pet gender (M/F):");
        String gender = scanner.nextLine();
        ConsoleUI.printPrompt("Enter pet description:");
        String description = scanner.nextLine();

        Pet newPet = new Pet(petManager.getNextPetId(), name, species, breed, age, gender, description);
        petManager.addPet(newPet);
        ConsoleUI.printSuccess("Pet added successfully!");
        
        ConsoleUI.pauseExecution();
    }

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