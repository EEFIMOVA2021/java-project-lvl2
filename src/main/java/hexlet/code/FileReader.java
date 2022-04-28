package hexlet.code;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {
    public static String getDataFromFile(String filepath) throws Exception {
        String str = "";
        try {
            Path path = Paths.get(filepath);
            str = Files.readString(path);
        } catch (Exception e) {
            System.out.println("An error has ossured in getJsonFromFile()!");
            e.printStackTrace();
        }
        return str;
    }
}
