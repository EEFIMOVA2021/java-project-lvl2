package hexlet.code;

import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.LinkedHashMap;

public class Differ {
    public static String generate(String filepath1, String filepath2) {
        String generateResult = "";
        try {
            Map<String, Object> map1 = Parser.parseFile(filepath1);
            Map<String, Object> map2 = Parser.parseFile(filepath2);
            Map<String, String> res = genDiff(map1, map2);
            generateResult = getStringValuesMap(res);
        } catch (Exception e) {
            System.out.println("An error has ossured in main()!");
            e.printStackTrace();
        }
        return generateResult;
    }

    private static Map<String, String> genDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, String> result = new LinkedHashMap<>();
        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());
        for (String key: keys) {
            if (!map1.containsKey(key)) {
                result.put(key, "+ " + key + ": " + map2.get(key));
            } else if (!map2.containsKey(key)) {
                result.put(key, "- " + key + ": " + map1.get(key));
            } else if (map1.get(key).equals(map2.get(key))) {
                result.put(key, "  " + key + ": " + map1.get(key));
            } else {
                result.put(key, "- " + key + ": " + map1.get(key) + "\n+ " + key + ": " + map2.get(key));
            }
        }
        return result;
    }

    private static String getStringValuesMap(Map<String, String> map) {
        String result = "{";
        for (Map.Entry<String, String> mapEntity: map.entrySet()) {
            result += "\n" + mapEntity.getValue();
        }
        result += "\n}";
        return result;
    }
}
