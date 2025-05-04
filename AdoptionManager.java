import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AdoptionManager {
    private CustomLinkedList<AdoptionRecord> adoptionRecords;
    private static final String ADOPTIONS_FILE = "adoptions.txt";

    public AdoptionManager() {
        adoptionRecords = new CustomLinkedList<>();
    }

    public void loadAdoptionsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ADOPTIONS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 8) {
                    int petId = Integer.parseInt(parts[0]);
                    String petName = parts[1];
                    String petSpecies = parts[2];
                    String petBreed = parts[3];
                    String adopterUsername = parts[4];
                    String adopterName = parts[5];
                    String adopterContact = parts[6];
                    String adoptionDate = parts[7];

                    AdoptionRecord record = new AdoptionRecord(petId, petName, petSpecies, petBreed,
                            adopterUsername, adopterName, adopterContact, adoptionDate);
                    adoptionRecords.add(record);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Adoptions file not found or corrupted. Creating a new one.");
            try {
                new FileWriter(ADOPTIONS_FILE).close();
            } catch (IOException ex) {
                System.out.println("Error creating adoptions file: " + ex.getMessage());
            }
        }
    }

    public void saveAdoptionsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ADOPTIONS_FILE))) {
            Node<AdoptionRecord> current = adoptionRecords.getHead();
            while (current != null) {
                writer.write(current.getData().toFileString());
                writer.newLine();
                current = current.getNext();
            }
        } catch (IOException e) {
            System.out.println("Error saving adoptions to file: " + e.getMessage());
        }
    }

    public void addAdoptionRecord(AdoptionRecord record) {
        adoptionRecords.add(record);
        saveAdoptionsToFile();
    }

    public void viewAllAdoptionRecords() {
        if (adoptionRecords.isEmpty()) {
            System.out.println("No adoption records found.");
            return;
        }

        System.out.println("\n===== ADOPTION RECORDS =====");
        Node<AdoptionRecord> current = adoptionRecords.getHead();
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    public void viewUserAdoptionHistory(String username) {
        boolean found = false;

        System.out.println("\n===== YOUR ADOPTION HISTORY =====");
        Node<AdoptionRecord> current = adoptionRecords.getHead();
        while (current != null) {
            if (current.getData().getAdopterUsername().equals(username)) {
                System.out.println(current.getData());
                found = true;
            }
            current = current.getNext();
        }

        if (!found) {
            System.out.println("You have not adopted any pets yet.");
        }
    }
}