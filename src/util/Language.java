package util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

public class Language {
    private static String language;
    private static int languageIndex;
    private static ArrayList<String> supportedLanguages = new ArrayList<String>();
    private static JSONObject translations = new JSONObject();


    private static void initializeTranslations() {

        JSONParser jsonParser = new JSONParser();
        URL url = Language.class.getResource("translations.json");
        File file = new File(url.getPath());

        try {
            FileReader reader = new FileReader(file);
            translations = (JSONObject) jsonParser.parse(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        JSONArray translation;
        try {
            translation = (JSONArray) translations.get(fieldName);
            fieldValue = (String) translation.get(languageIndex);
        } catch (Exception e) {
            System.out.println("That key does not exist in translations");
            e.printStackTrace();
            fieldValue = "Key Not Found";
        }
        return fieldValue;
    }

}
