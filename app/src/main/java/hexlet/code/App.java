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
    String filepath1;

    @Parameters(paramLabel = "filepath2",
            description = "path to second file")
    String filepath2;

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
            File fileObject1 = new File("app/src/main/java/hexlet/code/filepath1.json");
            Scanner myReader1 = new Scanner(fileObject1);
            String json1 = "";
            while(myReader1.hasNextLine()) {
                String data = myReader1.nextLine();
                json1 += data.trim();
            }
            myReader1.close();
            File fileObject2 = new File("app/src/main/java/hexlet/code/filepath2.json");
            Scanner myReader2 = new Scanner(fileObject2);
            String json2 = "";
            while(myReader2.hasNextLine()) {
                String data = myReader2.nextLine();
                json2 += data.trim();
            }
            myReader2.close();
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> map1 = objectMapper.readValue(json1, Map.class);
            Map<String, String> map2 = objectMapper.readValue(json2, Map.class);
            System.out.println(json1);
            System.out.println(map1);
            System.out.println(json2);
            System.out.println(map2);
            Map<String, String> res = genDiff(map1, map2);
            System.out.println(res);
        } catch (Exception e) {
            System.out.println("An error has ossured!");
            e.printStackTrace();
        }
        System.exit(exitCode);
    }

    public static Map<String, String> genDiff(Map<String, String> map1, Map<String, String> map2) {
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
