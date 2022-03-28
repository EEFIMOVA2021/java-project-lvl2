package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Parser {
    public static String getDataFromFile(String filepath) throws Exception {
        String json = "";
        try {
            Path path = Paths.get(filepath);
            json = Files.readString(path);
        } catch (Exception e) {
            System.out.println("An error has ossured in getJsonFromFile()!");
            e.printStackTrace();
        }
        return json;
    }

    public static Map<String, Object> parseFile(String filepath)  throws Exception {
        String dataFromFile = getDataFromFile(filepath);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(dataFromFile, Map.class);
        return map;
    }
}
