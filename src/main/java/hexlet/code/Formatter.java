package hexlet.code;

import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class Formatter {
    public static String getStringFormat(Map<String, List<String>> map, String format) {
        if (format == "stylish") {
            return getStringValuesStylish(map);
        } else {
            return getStringValuesPlain(map);
        }
    }

    private static String getStringValuesStylish(Map<String, List<String>> map) {
        String result = "{";
        for (Map.Entry<String, List<String>> mapEntity: map.entrySet()) {
            List<String> list = new LinkedList<>();
            list.addAll(mapEntity.getValue());
            if (list.get(0) == "add") {
                result += "\n" + "+ " + mapEntity.getKey() + ": " + list.get(2);
            } else if (list.get(0) == "remove") {
                result += "\n" + "- " + mapEntity.getKey() + ": " + list.get(1);
            } else if (list.get(0) == "change") {
                result += "\n" + "- " + mapEntity.getKey() + ": " + list.get(1)
                        + "\n+ " + mapEntity.getKey() + ": " + list.get(2);
            } else {
                result += "\n" + "  " + mapEntity.getKey() + ": " + list.get(1);
            }
        }
        result += "\n}";
        return result;
    }

    private static String getStringValuesPlain(Map<String, List<String>> map) {
        String result = "";
        for (Map.Entry<String, List<String>> mapEntity: map.entrySet()) {
            List<String> list = new LinkedList<>();
            list.addAll(mapEntity.getValue());
            if (list.get(0) == "add") {
                result += "Property '" + mapEntity.getKey() + "' was added with value: " + getNewListValue(list);
            } else if (list.get(0) == "remove") {
                result += "Property '" + mapEntity.getKey() + "' was removed";
            } else if (list.get(0) == "change") {
                result += "Property '" + mapEntity.getKey() + "' was updated. From " + getOldListValue(list)
                        + " to " + getNewListValue(list);
            }
            if (list.get(0) != "unchange") {
                result += "\n";
            }
        }
        return result;
    }

    private static String getOldListValue (List<String> list) {
        if (list.get(3) == "noncomplex") {
            return list.get(1);
        }
        return list.get(3);
    }

    private static String getNewListValue (List<String> list) {
        if (list.get(4) == "noncomplex") {
            return list.get(2);
        }
        return list.get(4);
    }
}