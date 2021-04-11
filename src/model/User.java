package model;

public class User {
    private static int id;
    private static String username;

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean authenticate(String username) {
        if (username == this.username) {
            return true;
        } else {
            return false;
        }
    }
}
