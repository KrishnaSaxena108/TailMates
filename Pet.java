public class Pet {
    private int id;
    private String name;
    private String species;
    private String breed;
    private int age;
    private String gender;
    private String description;

    public Pet(int id, String name, String species, String breed, int age, String gender, String description) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.gender = gender;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Name: " + name +
                " | Species: " + species +
                " | Breed: " + breed +
                " | Age: " + age +
                " | Gender: " + gender +
                " | Description: " + description;
    }

    public String toFileString() {
        return id + "," + name + "," + species + "," + breed + "," + age + "," + gender + "," + description;
    }
}