import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Authentication {
    private CustomLinkedList<User> users;
    private static final String USERS_FILE = "users.csv";
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    public Authentication() {
        users = new CustomLinkedList<>();
    }

    public void loadUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    User user = new User(parts[0], parts[1], parts[2], parts[3]);
                    users.add(user);
                }
            }
        } catch (IOException e) {
            // System.out.println("Initializing users database...");
            try {
                new FileWriter(USERS_FILE).close();
            } catch (IOException ex) {
                System.out.println("Error creating users file: " + ex.getMessage());
            }
        }
    }

    public void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            Node<User> current = users.getHead();
            while (current != null) {
                writer.write(current.getData().toFileString());
                writer.newLine();
                current = current.getNext();
            }
        } catch (IOException e) {
            System.out.println("Error saving users to file: " + e.getMessage());
        }
    }

    public User authenticateUser(String username, String password) {
        Node<User> current = users.getHead();
        while (current != null) {
            User user = current.getData();
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
            current = current.getNext();
        }
        return null;
    }

    public boolean authenticateAdmin(String username, String password) {
        return username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD);
    }

    public boolean registerUser(User newUser) {
        if (isUsernameExists(newUser.getUsername())) {
            return false;
        }

        users.add(newUser);
        saveUsersToFile();
        return true;
    }

    private boolean isUsernameExists(String username) {
        Node<User> current = users.getHead();
        while (current != null) {
            if (current.getData().getUsername().equals(username)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
}