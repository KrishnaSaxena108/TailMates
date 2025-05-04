import java.text.SimpleDateFormat;
import java.util.Date;

public class AdoptionRecord {
    private Pet pet;
    private String adopterUsername;
    private String adopterName;
    private String adopterContact;
    private String adoptionDate;

    public AdoptionRecord(Pet pet, String adopterUsername, String adopterName, String adopterContact) {
        this.pet = pet;
        this.adopterUsername = adopterUsername;
        this.adopterName = adopterName;
        this.adopterContact = adopterContact;
        this.adoptionDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public AdoptionRecord(int petId, String petName, String petSpecies, String petBreed,
            String adopterUsername, String adopterName, String adopterContact, String adoptionDate) {
        this.pet = new Pet(petId, petName, petSpecies, petBreed, 0, "", "");
        this.adopterUsername = adopterUsername;
        this.adopterName = adopterName;
        this.adopterContact = adopterContact;
        this.adoptionDate = adoptionDate;
    }

    public Pet getPet() {
        return pet;
    }

    public String getAdopterUsername() {
        return adopterUsername;
    }

    public String getAdopterName() {
        return adopterName;
    }

    public String getAdopterContact() {
        return adopterContact;
    }

    public String getAdoptionDate() {
        return adoptionDate;
    }

    @Override
    public String toString() {
        return "Pet: " + pet.getName() + " (" + pet.getSpecies() + ", " + pet.getBreed() + ")" +
                " | Adopter: " + adopterName +
                " | Contact: " + adopterContact +
                " | Date: " + adoptionDate;
    }

    public String toFileString() {
        return pet.getId() + "," + pet.getName() + "," + pet.getSpecies() + "," + pet.getBreed() + "," +
                adopterUsername + "," + adopterName + "," + adopterContact + "," + adoptionDate;
    }
}