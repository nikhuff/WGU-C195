package model;

public class User {

    private static int id;
    private static String username;
    private static boolean onLogon = false;

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public static int getId() {
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

    public static boolean getOnLogon() {
        return onLogon;
    }

    public static void setOnLogon(boolean loggedon) {
        onLogon = true;
    }
}
