package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import java.util.*;

@Command(name = "App", version = "App 1.0",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")

public class App implements Runnable{
    @Option(names = { "-f", "--format" }, description = "output format [default: stylish]")
    String format = "stylish";

    @Parameters(paramLabel = "filepath1",
            description = "path to first file")
    private static String filepath1;

    @Parameters(paramLabel = "filepath2",
            description = "path to second file")
    private static String filepath2;

    public String getGreeting() {
        return "Hello World!";
    }

    @Override
    public void run() {
    }

    public static void main(String[] args) {
        //System.out.println(new App().getGreeting());
        int exitCode = new CommandLine(new App()).execute(args);
        try {
            String json1 = getJsonFromFile(filepath1); //"src/main/java/hexlet/code/filepath1.json"
            String json2 = getJsonFromFile(filepath2); //"src/main/java/hexlet/code/filepath2.json"
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> map1 = objectMapper.readValue(json1, Map.class);
            Map<String, String> map2 = objectMapper.readValue(json2, Map.class);
            System.out.println(map1);
            System.out.println(map2);
            Map<String, String> res = genDiff(map1, map2);
            System.out.println(res);
        } catch (Exception e) {
            System.out.println("An error has ossured in main()!");
            e.printStackTrace();
        }
        System.exit(exitCode);
    }

    private static String getJsonFromFile(String filepath) throws Exception {
        String json = "";
        try {
            File fileObject = new File(filepath);
            Scanner myReader = new Scanner(fileObject);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                json += data.trim();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error has ossured in getJsonFromFile()!");
            e.printStackTrace();
        }
        return json;
    }

    private static Map<String, String> genDiff(Map<String, String> map1, Map<String, String> map2) {
        Map<String, String> result = new LinkedHashMap<>();
        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());
        for (String key: keys) {
            if(!map1.containsKey(key)) {
                result.put(key, "added");
            } else if (!map2.containsKey(key)) {
                result.put(key, "deleted");
            } /*else if (map1.get(key).equals(map2.get(key))) {
                result.put(key, "unchanged");
            } else {
                result.put(key, "changed");
            }*/
        }
        return result;
    }
}
