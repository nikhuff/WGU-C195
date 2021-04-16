package util;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class Logger {

    public static void logAttempt(String username, boolean success) {

        String attempt = success ? "Success" : "Fail";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String log = timestamp + " Username: " + username + " | Attempt: " + attempt + "\n";

        try {
            FileWriter writer = new FileWriter("login_activity.txt", true);
            writer.write(log);
            writer.close();
            System.out.println("Wrote to file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
