package hexlet.code.formatter;

import java.util.Map;
import java.util.List;

public class Plain {
    private static boolean firstEnter;

    public static String render(List<Map<String, Object>> list) {
        firstEnter = true;
        String result = "";
        for (Map<String, Object> map: list) {
            result += getLineBreak(map) + getValueForStatus(map);
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

    private static String getLineBreak(Map<String, Object> map) {
        String result = "";
        if (!map.get("status").equals("unchange")) {
            if (!firstEnter) {
                result += "\n";
            }
            firstEnter = false;
        }
        return result;
    }
}
