/**
 * A utility class for creating better console UI elements
 */
public class ConsoleUI {
    // ANSI color codes for console text formatting
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    
    // Text styles
    public static final String BOLD = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";
    
    /**
     * Clears the console screen
     * Note: This may not work in all environments
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    /**
     * Prints a header with a title
     */
    public static void printHeader(String title) {
        int width = 60;
        String line = "=".repeat(width);
        
        System.out.println("\n" + BOLD + BLUE + line + RESET);
        
        // Calculate padding to center the title
        int padding = (width - title.length()) / 2;
        String paddedTitle = " ".repeat(padding) + title + " ".repeat(padding);
        // Adjust if odd length
        if (paddedTitle.length() < width) {
            paddedTitle += " ";
        }
        
        System.out.println(BOLD + BLUE + "|" + CYAN + paddedTitle + BLUE + "|" + RESET);
        System.out.println(BOLD + BLUE + line + RESET + "\n");
    }
    
    /**
     * Prints a menu option
     */
    public static void printMenuItem(int number, String description) {
        System.out.println(BOLD + YELLOW + "  " + number + ". " + RESET + description);
    }
    
    /**
     * Prints a formatted pet card
     */
    public static void printPetCard(Pet pet) {
        int width = 60;
        String line = "-".repeat(width);
        
        System.out.println(CYAN + line + RESET);
        
        // ID and Name header
        System.out.println(BOLD + "│ #" + pet.getId() + ": " + 
                          GREEN + pet.getName() + RESET + BOLD + 
                          " (" + pet.getGender() + ")" + RESET);
        
        // Species and breed
        System.out.println("│ " + YELLOW + pet.getSpecies() + RESET + 
                          " - " + pet.getBreed() + 
                          ", Age: " + pet.getAge());
        
        // Description with word wrapping
        System.out.println("│");
        System.out.println("│ " + BOLD + "Description:" + RESET);
        
        // Simple word wrapping for description
        String desc = pet.getDescription();
        int maxLineLength = width - 4; // accounting for "│ " prefix
        
        for (int i = 0; i < desc.length(); i += maxLineLength) {
            String line1 = desc.substring(i, Math.min(desc.length(), i + maxLineLength));
            System.out.println("│ " + line1);
        }
        
        System.out.println(CYAN + line + RESET);
    }
    
    /**
     * Prints a prompt for user input
     */
    public static void printPrompt(String message) {
        System.out.print(BOLD + PURPLE + "► " + message + " " + RESET);
    }
    
    /**
     * Prints a success message
     */
    public static void printSuccess(String message) {
        System.out.println(GREEN + "✓ " + message + RESET);
    }
    
    /**
     * Prints an error message
     */
    public static void printError(String message) {
        System.out.println(RED + "✗ " + message + RESET);
    }
    
    /**
     * Prints information message
     */
    public static void printInfo(String message) {
        System.out.println(BLUE + "ℹ " + message + RESET);
    }
    
    /**
     * Prints an adoption record with formatting
     */
    public static void printAdoptionRecord(AdoptionRecord record) {
        System.out.println(YELLOW + "┌─────────────────────────────────────────────────────┐" + RESET);
        System.out.println(YELLOW + "│ " + BOLD + "ADOPTION RECORD" + RESET + YELLOW + "                                  │" + RESET);
        System.out.println(YELLOW + "├─────────────────────────────────────────────────────┤" + RESET);
        System.out.println(YELLOW + "│ " + RESET + BOLD + "Pet:" + RESET + " " + GREEN + record.getPet().getName() + RESET + 
                          " (" + record.getPet().getSpecies() + ", " + record.getPet().getBreed() + ")");
        System.out.println(YELLOW + "│ " + RESET + BOLD + "Adopter:" + RESET + " " + record.getAdopterName());
        System.out.println(YELLOW + "│ " + RESET + BOLD + "Contact:" + RESET + " " + record.getAdopterContact());
        System.out.println(YELLOW + "│ " + RESET + BOLD + "Date:" + RESET + " " + record.getAdoptionDate());
        System.out.println(YELLOW + "└─────────────────────────────────────────────────────┘" + RESET);
    }
    
    /**
     * Prints a welcome animation
     * Simple ASCII art animation for the welcome screen
     */
    public static void printWelcomeAnimation() {
        clearScreen();
        try {
            String[] frames = {
                "  /\\_/\\\n (o.o )\n  > ^ <  Welcome to Pet Adoption System",
                "  /\\_/\\\n (^.^ )\n  > ^ <  Welcome to Pet Adoption System",
                "  /\\_/\\\n (-.o )\n  > ^ <  Welcome to Pet Adoption System",
                "  /\\_/\\\n (^.^ )\n  > ^ <  Welcome to Pet Adoption System"
            };
            
            for (int i = 0; i < 5; i++) {
                for (String frame : frames) {
                    clearScreen();
                    System.out.println(CYAN + frame + RESET);
                    Thread.sleep(200);
                }
            }
        } catch (InterruptedException e) {
            // Do nothing on interrupted exception
        }
        clearScreen();
    }
    
    /**
     * Pauses execution and waits for user to press Enter
     */
    public static void pauseExecution() {
        System.out.println(PURPLE + "\nPress Enter to continue..." + RESET);
        try {
            System.in.read();
            // Clear the buffer
            while (System.in.available() > 0) {
                System.in.read();
            }
        } catch (Exception e) {
            // Do nothing
        }
    }
}