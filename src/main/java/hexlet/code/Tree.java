package hexlet.code;

import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Tree {
    public static List<Map<String, Object>> build(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> resultList = new LinkedList<>();
        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());
        for (String key : keys) {
            Map<String, Object> resultMap = getResultMap(map1, map2, key);
            resultList.add(resultMap);
        }
        return resultList;
    }

    public static Map<String, Object> getResultMap(Map<String, Object> map1, Map<String, Object> map2, String key) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("key", key);
        resultMap.put("oldValue", map1.get(key));
        resultMap.put("newValue", map2.get(key));
        resultMap.put("status", calcStatus(map1, map2, key));
        return resultMap;
    }

    public static String calcStatus(Map<String, Object> map1, Map<String, Object> map2, String key) {
        String result = "";
        if (!map1.containsKey(key)) {
            result = "add";
        } else if (!map2.containsKey(key)) {
            result = "remove";
        } else if (calcChangeStatus(map1, map2, key)) {
            result = "change";
        } else {
            result = "unchange";
        }
        return result;
    }

    private static boolean calcChangeStatus(Map<String, Object> map1, Map<String, Object> map2, String key) {
        return map1.get(key) == null && map2.get(key) != null
                || map1.get(key) != null && map2.get(key) == null
                || map1.get(key) != null && map2.get(key) != null && !map1.get(key).equals(map2.get(key));
    }
}
