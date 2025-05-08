import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PetManager {
    private CustomLinkedList<Pet> pets;
    private static final String PETS_FILE = "pets.csv";
    private int nextPetId;

    public PetManager() {
        pets = new CustomLinkedList<>();
        nextPetId = 1;
    }

    public void loadPetsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PETS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String species = parts[2];
                    String breed = parts[3];
                    int age = Integer.parseInt(parts[4]);
                    String gender = parts[5];
                    String description = parts[6];

                    Pet pet = new Pet(id, name, species, breed, age, gender, description);
                    pets.add(pet);

                    if (id >= nextPetId) {
                        nextPetId = id + 1;
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Pets file not found or corrupted. Creating a new one.");
            try {
                new FileWriter(PETS_FILE).close();
            } catch (IOException ex) {
                System.out.println("Error creating pets file: " + ex.getMessage());
            }
        }
    }

    public void savePetsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PETS_FILE))) {
            Node<Pet> current = pets.getHead();
            while (current != null) {
                writer.write(current.getData().toFileString());
                writer.newLine();
                current = current.getNext();
            }
        } catch (IOException e) {
            System.out.println("Error saving pets to file: " + e.getMessage());
        }
    }

    public void addPet(Pet pet) {
        pets.add(pet);
        savePetsToFile();
    }

    // Method to update a pet
    public void updatePet(Pet updatedPet) {
        Node<Pet> current = pets.getHead();

        while (current != null) {
            if (current.getData().getId() == updatedPet.getId()) {
                // Replace the pet data with updated data
                current.setData(updatedPet);
                savePetsToFile();
                return;
            }
            current = current.getNext();
        }
    }

    public boolean removePet(int petId) {
        Node<Pet> current = pets.getHead();
        Node<Pet> previous = null;

        while (current != null) {
            if (current.getData().getId() == petId) {
                if (previous == null) {
                    pets.setHead(current.getNext());
                } else {
                    previous.setNext(current.getNext());
                }
                savePetsToFile();
                return true;
            }
            previous = current;
            current = current.getNext();
        }
        return false;
    }

    public Pet getPetById(int petId) {
        Node<Pet> current = pets.getHead();
        while (current != null) {
            if (current.getData().getId() == petId) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    public void displayAvailablePets() {
        if (pets.isEmpty()) {
            System.out.println("No pets available for adoption.");
            return;
        }

        System.out.println("\n===== AVAILABLE PETS =====");
        Node<Pet> current = pets.getHead();
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    public int getPetCount() {
        int count = 0;
        Node<Pet> current = pets.getHead();
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    public int getNextPetId() {
        return nextPetId++;
    }

    public CustomLinkedList<Pet> getPets() {
        return pets;
    }
}