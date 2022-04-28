package hexlet.formatter;

import java.util.Map;
import java.util.List;

public class Plain {
    public static String getStringValuesPlain(List<Map<String, Object>> list) {
        boolean firstEnter = true;
        String result = "";
        for (Map<String, Object> map: list) {
            if (!map.get("status").equals("unchange") && !firstEnter) {
                result += "\n";
            }
            if (!map.get("status").equals("unchange")) {
                firstEnter = false;
            }
            result += getValueForStatus(map);
        }
        return result;
    }

    private static String getValueForStatus(Map<String, Object> map) {
        if (map.get("status").equals("add")) {
            return "Property '" + map.get("key")
                    + "' was added with value: " + getValue(map.get("newValue"));
        } else if (map.get("status").equals("remove")) {
            return "Property '" + map.get("key") + "' was removed";
        } else if (map.get("status").equals("change")) {
            return "Property '" + map.get("key") + "' was updated. From "
                    + getValue(map.get("oldValue"))
                    + " to " + getValue(map.get("newValue"));
        }
        return "";
    }

    private static String getValue(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value instanceof Integer || value instanceof Boolean) {
            return value.toString();
        }
        return "[complex value]";
    }
}
