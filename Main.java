import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Authentication auth = new Authentication();
    private static PetManager petManager = new PetManager();
    private static AdoptionManager adoptionManager = new AdoptionManager();

    public static void main(String[] args) {
        initializeSystem();

        boolean running = true;
        while (running) {
            System.out.println("\n===== PET ADOPTION SYSTEM =====");
            System.out.println("1. Login as User");
            System.out.println("2. Login as Admin");
            System.out.println("3. Register as User");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

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
                        System.out.println("Thank you for using Pet Adoption System!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static void initializeSystem() {
        petManager.loadPetsFromFile();
        auth.loadUsersFromFile();
        adoptionManager.loadAdoptionsFromFile();
    }

    private static void userLogin() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = auth.authenticateUser(username, password);
        if (user != null) {
            System.out.println("Login successful! Welcome " + user.getName());
            userMenu(user);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void adminLogin() {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        if (auth.authenticateAdmin(username, password)) {
            System.out.println("Admin login successful!");
            adminMenu();
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }

    private static void registerUser() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter contact number: ");
        String contact = scanner.nextLine();

        User newUser = new User(name, username, password, contact);
        if (auth.registerUser(newUser)) {
            System.out.println("Registration successful! You can now login.");
        } else {
            System.out.println("Username already exists. Please choose a different username.");
        }
    }

    private static void userMenu(User user) {
        boolean userSession = true;

        while (userSession) {
            System.out.println("\n===== USER MENU =====");
            System.out.println("1. View Available Pets");
            System.out.println("2. Adopt a Pet");
            System.out.println("3. Add a Pet for Adoption");
            System.out.println("4. View My Adoption History");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        petManager.displayAvailablePets();
                        break;
                    case 2:
                        adoptPet(user);
                        break;
                    case 3:
                        addPetForAdoption();
                        break;
                    case 4:
                        adoptionManager.viewUserAdoptionHistory(user.getUsername());
                        break;
                    case 5:
                        userSession = false;
                        System.out.println("Logged out successfully.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static void adminMenu() {
        boolean adminSession = true;

        while (adminSession) {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. View All Available Pets");
            System.out.println("2. View All Adoption Records");
            System.out.println("3. Remove a Pet from System");
            System.out.println("4. Add a Pet to System");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        petManager.displayAvailablePets();
                        break;
                    case 2:
                        adoptionManager.viewAllAdoptionRecords();
                        break;
                    case 3:
                        removePet();
                        break;
                    case 4:
                        addPetForAdoption();
                        break;
                    case 5:
                        adminSession = false;
                        System.out.println("Logged out successfully.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static void adoptPet(User user) {
        petManager.displayAvailablePets();

        if (petManager.getPetCount() == 0) {
            System.out.println("No pets available for adoption.");
            return;
        }

        System.out.print("Enter the ID of the pet you want to adopt: ");
        try {
            int petId = Integer.parseInt(scanner.nextLine());
            Pet pet = petManager.getPetById(petId);

            if (pet != null) {
                System.out.println("You are adopting: " + pet.getName() + " (" + pet.getSpecies() + ")");
                System.out.print("Confirm adoption (y/n): ");
                String confirm = scanner.nextLine();

                if (confirm.equalsIgnoreCase("y")) {
                    AdoptionRecord record = new AdoptionRecord(pet, user.getUsername(), user.getName(),
                            user.getContact());
                    adoptionManager.addAdoptionRecord(record);
                    petManager.removePet(petId);
                    System.out.println("Congratulations! You have successfully adopted " + pet.getName() + ".");
                } else {
                    System.out.println("Adoption cancelled.");
                }
            } else {
                System.out.println("Pet with ID " + petId + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid pet ID.");
        }
    }

    private static void addPetForAdoption() {
        System.out.print("Enter pet name: ");
        String name = scanner.nextLine();
        System.out.print("Enter pet species (dog/cat/bird/etc.): ");
        String species = scanner.nextLine();
        System.out.print("Enter pet breed: ");
        String breed = scanner.nextLine();
        System.out.print("Enter pet age: ");
        int age = 0;
        try {
            age = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid age. Setting age to 0.");
        }
        System.out.print("Enter pet gender (M/F): ");
        String gender = scanner.nextLine();
        System.out.print("Enter pet description: ");
        String description = scanner.nextLine();

        Pet newPet = new Pet(petManager.getNextPetId(), name, species, breed, age, gender, description);
        petManager.addPet(newPet);
        System.out.println("Pet added successfully!");
    }

    private static void removePet() {
        petManager.displayAvailablePets();

        if (petManager.getPetCount() == 0) {
            System.out.println("No pets available in the system.");
            return;
        }

        System.out.print("Enter the ID of the pet you want to remove: ");
        try {
            int petId = Integer.parseInt(scanner.nextLine());
            if (petManager.removePet(petId)) {
                System.out.println("Pet removed successfully.");
            } else {
                System.out.println("Pet with ID " + petId + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid pet ID.");
        }
    }
}