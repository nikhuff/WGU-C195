package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Language {
    private static String language;
    private static int languageIndex;
    private static ArrayList<String> supportedLanguages = new ArrayList<String>();
    private static HashMap<String, ArrayList<String>> translations = new HashMap<String, ArrayList<String>>();

    private static void initializeTranslations() {
        ArrayList<String> loginTitle = new ArrayList<String>();
        loginTitle.add("Login");
        loginTitle.add("Connexion");
        translations.put("Login Title", loginTitle);

        ArrayList<String> password = new ArrayList<String>();
        password.add("password");
        password.add("le mot de passe");
        translations.put("Password", password);

        ArrayList<String> username = new ArrayList<String>();
        username.add("username");
        username.add("nom d'utilisateur");
        translations.put("Username", username);

        ArrayList<String> stageTitle = new ArrayList<String>();
        stageTitle.add("Appointment Scheduler");
        stageTitle.add("Planificateur de rendez-vous");
        translations.put("Stage Title", stageTitle);
    }

    public static void init() {
        language = Locale.getDefault().getLanguage();
        supportedLanguages.add("en");
        supportedLanguages.add("fr");
        languageIndex = supportedLanguages.indexOf(language);
        initializeTranslations();
    }

    public static String getField(String fieldName) {
        String fieldValue;
        try {
            fieldValue = translations.get(fieldName).get(languageIndex);
        } catch (Exception e) {
            System.out.println("That key does not exist in translations");
            e.printStackTrace();
            fieldValue = "Key Not Found";
        }
        return fieldValue;
    }

}
