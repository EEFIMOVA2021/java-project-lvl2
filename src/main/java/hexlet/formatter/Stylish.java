package hexlet.formatter;

import java.util.Map;
import java.util.List;

public class Stylish {
    public static String getStringValuesStylish(List<Map<String, Object>> list) {
        String result = "{";
        for (Map<String, Object> map: list) {
            result += getValueForStatus(map);
        }
        result += "\n}";
        return result;
    }

    private static String getValueForStatus(Map<String, Object> map) {
        if (map.get("status") == "add") {
            return "\n  + " + map.get("key") + ": " + map.get("newValue");
        } else if (map.get("status") == "remove") {
            return "\n  - " + map.get("key") + ": " + map.get("oldValue");
        } else if (map.get("status") == "change") {
            return "\n  - " + map.get("key") + ": " + map.get("oldValue")
                    + "\n  + " + map.get("key") + ": " + map.get("newValue");
        } else {
            return "\n    " + map.get("key") + ": " + map.get("oldValue");
        }
    }
}
