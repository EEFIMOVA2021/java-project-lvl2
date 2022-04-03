package hexlet.code;

import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) {
        String generateResult = "";
        try {
            Map<String, Object> map1 = Parser.parseFile(filepath1);
            Map<String, Object> map2 = Parser.parseFile(filepath2);
            Map<String, List<String>> res = genDiff(map1, map2);
            generateResult = Formatter.getStringFormat(res, format);
        } catch (Exception e) {
            System.out.println("An error has ossured in main()!");
            e.printStackTrace();
        }
        return generateResult;
    }

    private static Map<String, List<String>> genDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, List<String>> result = new LinkedHashMap<>();
        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());
        for (String key: keys) {
            List<String> listValues = new LinkedList<>();
            if (!map1.containsKey(key)) {
                listValues.add("add");
                listValues.add("");
                listValues.add(map2.get(key).toString());
            } else if (!map2.containsKey(key)) {
                listValues.add("remove");
                listValues.add(map1.get(key).toString());
                listValues.add("");
            } else if (map1.get(key) == null && map2.get(key) != null) {
                listValues.add("change");
                listValues.add("null");
                listValues.add(map2.get(key).toString());
            } else if (map1.get(key) != null && map2.get(key) == null) {
                listValues.add("change");
                listValues.add(map1.get(key).toString());
                listValues.add("null");
            } else if (map1.get(key) == null && map2.get(key) == null || map1.get(key).equals(map2.get(key))) {
                listValues.add("unchange");
                if (map1.get(key) == null) {
                    listValues.add("null");
                } else {
                    listValues.add(map1.get(key).toString());
                }
                if (map2.get(key) == null) {
                    listValues.add("null");
                } else {
                    listValues.add(map2.get(key).toString());
                }
            } else {
                listValues.add("change");
                listValues.add(map1.get(key).toString());
                listValues.add(map2.get(key).toString());
            }
            if (map1.get(key) instanceof Integer
                    || map1.get(key) instanceof String
                    || map1.get(key) instanceof Boolean
                    || map1.get(key) == null) {
                listValues.add("noncomplex");
            } else {
                listValues.add("[complex value]");
            }
            if (map2.get(key) instanceof Integer
                    || map2.get(key) instanceof String
                    || map2.get(key) instanceof Boolean
                    || map2.get(key) == null) {
                listValues.add("noncomplex");
            } else {
                listValues.add("[complex value]");
            }
            result.put(key, listValues);
        }
        //System.out.println(result);
        return result;
    }
}
