public class User {
    private String name;
    private String username;
    private String password;
    private String contact;

    public User(String name, String username, String password, String contact) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getContact() {
        return contact;
    }

    public String toFileString() {
        return name + "," + username + "," + password + "," + contact;
    }
}